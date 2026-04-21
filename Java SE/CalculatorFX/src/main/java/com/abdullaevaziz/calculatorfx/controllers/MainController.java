package com.abdullaevaziz.calculatorfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    public TextField dataInput1;
    @FXML
    public TextField dataInput2;
    @FXML
    public TextField result;
    @FXML
    public Label dataInputTxt1;
    @FXML
    public Label dataInputTxt2;
    @FXML
    public Button plus;
    @FXML
    public Button minus;
    @FXML
    public Button multiply;
    @FXML
    public Button divisions;
    @FXML
    public Label arithmeticOperationSign;

    @FXML
    public void plus(ActionEvent actionEvent) {
        int input1 = Integer.parseInt(dataInput1.getText());
        int input2 = Integer.parseInt(dataInput2.getText());

        int res1 = input1 + input2;

        this.result.setText(String.valueOf(res1));
    }
    @FXML
    public void minus(ActionEvent actionEvent) {
        int input1 = Integer.parseInt(dataInput1.getText());
        int input2 = Integer.parseInt(dataInput2.getText());

        int res1 = input1 - input2;

        this.result.setText(String.valueOf(res1));
    }
    @FXML
    public void multiply(ActionEvent actionEvent) {
        int input1 = Integer.parseInt(dataInput1.getText());
        int input2 = Integer.parseInt(dataInput2.getText());

        int res1 = input1 * input2;

        this.result.setText(String.valueOf(res1));
    }
    @FXML
    public void divisions(ActionEvent actionEvent) {
        int input1 = Integer.parseInt(dataInput1.getText());
        int input2 = Integer.parseInt(dataInput2.getText());

        int res1 = input1 / input2;

        this.result.setText(String.valueOf(res1));
    }


}
