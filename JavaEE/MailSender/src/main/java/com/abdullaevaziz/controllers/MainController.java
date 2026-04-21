package com.abdullaevaziz.controllers;

import com.abdullaevaziz.app.App;
import com.abdullaevaziz.model.Letter;
import com.abdullaevaziz.service.MailSenderService;
import com.abdullaevaziz.util.MailSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainController {
    @FXML
    public TextField topicLetters;
    @FXML
    public TextField textLetters;
    @FXML
    public TextArea listRecipients;
    @FXML
    public ListView<Letter> listSendLetters;
    private MailSenderService mailSenderService = new MailSenderService();

    @FXML
    public void buttonSend(ActionEvent actionEvent) {
        String topic = topicLetters.getText();
        String text = textLetters.getText();
        String users = listRecipients.getText();

        Letter letter = new Letter(topic, text, users);
        mailSenderService.add(letter);

        this.listSendLetters.getItems().add(letter);
    }


}
