package com.kirillkotov.opennewformsjavafxlect.controllers;

import com.kirillkotov.opennewformsjavafxlect.App;
import com.kirillkotov.opennewformsjavafxlect.model.TV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {
    @FXML
    public void button(ActionEvent actionEvent) {
        try {
            TV tv = new TV("Samsung", "K900", 10);
            App.openWindow("second.fxml", "TV In@fo", tv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
