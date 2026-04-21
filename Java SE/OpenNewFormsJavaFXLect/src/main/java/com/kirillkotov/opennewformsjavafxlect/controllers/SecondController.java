package com.kirillkotov.opennewformsjavafxlect.controllers;

import com.kirillkotov.opennewformsjavafxlect.App;
import com.kirillkotov.opennewformsjavafxlect.model.TV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondController implements ControllerData<TV> {
    @FXML
    public TextField textFieldBrand;
    @FXML
    public TextField textFieldTimeExpectancy;
    @FXML
    public TextField textFieldModel;
    private TV value;

    @Override
    public void initData(TV value) {
        this.value = value;
        textFieldBrand.setText(value.getBrand());
        textFieldModel.setText(value.getModel());
        textFieldTimeExpectancy.setText(Integer.toString(value.getTimeExpectancy()));
    }

    @FXML
    public void buttonClose(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
    }
}
