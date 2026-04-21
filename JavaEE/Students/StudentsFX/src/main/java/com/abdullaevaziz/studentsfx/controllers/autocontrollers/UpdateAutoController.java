package com.abdullaevaziz.studentsfx.controllers.autocontrollers;

import com.abdullaevaziz.studentsfx.App;
import com.abdullaevaziz.studentsfx.controllers.ControllerData;
import com.abdullaevaziz.studentsfx.model.Auto;
import com.abdullaevaziz.studentsfx.repository.AutoRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class UpdateAutoController implements ControllerData<Auto> {
    @FXML
    public Label idAutoLabel;
    @FXML
    public TextField idAutoTextField;
    @FXML
    public Label brandLabel;
    @FXML
    public TextField brandTextField;
    @FXML
    public Label powerLabel;
    @FXML
    public TextField powerTextField;
    @FXML
    public Label yearLabel;
    @FXML
    public TextField yearTextField;
    @FXML
    public Label idStudentLabel;
    @FXML
    public TextField idStudentTextField;


    private Auto auto;
    private AutoRepository autoRepository = new AutoRepository();


    @Override
    public void initData(Auto value) {
        this.auto = value;
        idAutoTextField.setText(String.valueOf(value.getId()));
        brandTextField.setText(value.getBrand());
        powerTextField.setText(String.valueOf(value.getPower()));
        yearTextField.setText(String.valueOf(value.getYear()));
        idStudentTextField.setText(String.valueOf(value.getId_s()));
    }

    @FXML
    public void addAuto(ActionEvent actionEvent) {
        auto.setBrand(brandTextField.getText());
        auto.setPower(Integer.parseInt(powerTextField.getText()));
        auto.setYear(Integer.parseInt(yearTextField.getText()));
        try {
            if (auto != null) {
                this.autoRepository.update(auto);
                App.closeWindow(actionEvent);

            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (Exception e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
