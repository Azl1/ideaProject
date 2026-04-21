package com.abdullaevaziz.chatspringbootfx.controllers;


import com.abdullaevaziz.chatspringbootfx.App;
import com.abdullaevaziz.chatspringbootfx.model.Message;
import com.abdullaevaziz.chatspringbootfx.retrofit.MessageRepository;
import com.abdullaevaziz.chatspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

public class OnlineUsersController  {
    @FXML
    public ListView<Long> getListViewOnline;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
    private Message message;



    public void initialize() {
        try {
            List<Long> res = new MessageRepository().getListOnline();
            System.out.println(res);
            this.getListViewOnline.setItems(FXCollections.observableList(new MessageRepository().getListOnline()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void buttonExit(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            App.openWindow("main.fxml", "Main info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
