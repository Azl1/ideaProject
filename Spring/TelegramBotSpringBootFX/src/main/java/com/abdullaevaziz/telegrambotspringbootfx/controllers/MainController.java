package com.abdullaevaziz.telegrambotspringbootfx.controllers;

import com.abdullaevaziz.telegrambotspringbootfx.App;
import com.abdullaevaziz.telegrambotspringbootfx.constants.Constants;
import com.abdullaevaziz.telegrambotspringbootfx.model.TelegramUser;
import com.abdullaevaziz.telegrambotspringbootfx.retrofit.AdminRepository;
import com.abdullaevaziz.telegrambotspringbootfx.retrofit.TelegramUserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.prefs.Preferences;

public class MainController {
    public ListView<TelegramUser> listView;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    private TelegramUserRepository telegramUserRepository = new TelegramUserRepository(login, password);

    @FXML
    public void initialize() {
        String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
        String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);

        try {
            this.listView.setItems(FXCollections.observableList(
                    new AdminRepository(login, password).getList()));
            this.listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            TelegramUser telegramUser = listView.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindow("telegramUser.fxml", "TelegramUser info", telegramUser);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonExit(ActionEvent actionEvent) {
        this.preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
        this.preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void buttonRemoveChat(ActionEvent actionEvent) {
        try {
            TelegramUser selectedItem = this.listView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select telegram user", Alert.AlertType.ERROR);
                return;
            }
            this.listView.getItems().remove(selectedItem);
            this.telegramUserRepository.deleteChat(selectedItem.getId());
            App.showAlert("Info!", "Telegram user successfully deleted!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
