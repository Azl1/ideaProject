package com.abdullaevaziz.demojavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    public TextField textFieldFIO;

    @FXML
    public TextField textFieldResult;

    @FXML
    public void buttonSave(ActionEvent actionEvent) {
        String fio = this.textFieldFIO.getText();
        String result = this.textFieldResult.getText();

        this.textFieldResult.setText(fio + " " + result);
    }
}
