package com.abdullaevaziz.chatspringbootfx.controllers;

import com.abdullaevaziz.chatspringbootfx.App;
import com.abdullaevaziz.chatspringbootfx.model.Message;
import com.abdullaevaziz.chatspringbootfx.model.User;
import com.abdullaevaziz.chatspringbootfx.retrofit.MessageRepository;
import com.abdullaevaziz.chatspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.chatspringbootfx.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.MessageEvent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

public class MainController {

    @FXML
    public ListView<Message> messageListView;
    @FXML
    public Label label;
    @FXML
    public Label textLabel;
    @FXML
    public TextField textField;
    @FXML
    public ListView<Long> listViewOnline;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);

    private long currentUserId = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
    private ObjectMapper objectMapper = new ObjectMapper();
    {
        this.objectMapper.registerModule(new JavaTimeModule());
    }


    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Initialize event source");
                    String url = "http://localhost:8081/sse/chat/" + currentUserId;
                    EventSource.Builder builder = new EventSource.Builder(new com.launchdarkly.eventsource.EventHandler() {

                        @Override
                        public void onOpen() {
                            System.out.println("onOpen");
                        }

                        @Override
                        public void onClosed() {
                            System.out.println("onClosed");
                        }

                        @Override
                        public void onMessage(String event, MessageEvent messageEvent) {
                            String json = messageEvent.getData();

                            try {
                                Message message = objectMapper.readValue(json, Message.class);
                                add(message);
                                add(currentUserId);
                            } catch (JsonProcessingException ignored) {}
                        }

                        @Override
                        public void onComment(String comment) {
                            System.out.println("onComment");
                        }

                        @Override
                        public void onError(Throwable t) {
                            System.out.println("onError: " + t);
                        }
                    }, URI.create(url));

                    try (EventSource eventSource = builder.build()) {
                        eventSource.start();
                        TimeUnit.MINUTES.sleep(1);
                    }
                }
            } catch (InterruptedException ignored) {

            }
        }
    });


    public void initialize() {
        this.thread.start();

        try {

            String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
            String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
            User user = new UserRepository(login, password).getUserId(currentUserId);

            System.out.println(user.getId());

            this.messageListView.setItems(FXCollections.observableList(new MessageRepository().getListMessage()));
            this.listViewOnline.setItems(FXCollections.observableList(new MessageRepository().getListOnline()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void buttonOnlineUsers(ActionEvent actionEvent) {
        //Message message = messageListView.getSelectionModel().getSelectedItem();
        try {
            App.openWindow("onlineUsers.fxml", "Online users info", null);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonSendMessage(ActionEvent actionEvent) {
        label.setText(String.valueOf(currentUserId));
        String text = textField.getText();
        Message newMessage = new Message(text);

        try {
            Message message = new MessageRepository().postMessage(currentUserId, newMessage);
            System.out.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exit(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Message message) {
        Platform.runLater(() ->{
            this.messageListView.getItems().add(message);
        });
    }

    public void add(Long id) {
        Platform.runLater(() ->{
            this.listViewOnline.getItems().add(id);
        });
    }
}
