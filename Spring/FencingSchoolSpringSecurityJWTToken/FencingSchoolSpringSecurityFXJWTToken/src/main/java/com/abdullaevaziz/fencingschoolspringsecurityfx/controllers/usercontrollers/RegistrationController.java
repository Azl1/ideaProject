package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers.usercontrollers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.ApprenticeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField surnameTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField patronymicTextField;
    @FXML
    public TextField phoneNumberTextField;

    private ApprenticeRepository apprenticeRepository = new ApprenticeRepository();

    @FXML
    public void addApprentice(ActionEvent actionEvent) {
        Apprentice newApprentice = new Apprentice(loginTextField.getText(), surnameTextField.getText(),
                nameTextField.getText(), patronymicTextField.getText(), passwordTextField.getText(), phoneNumberTextField.getText());
        try {
            Apprentice apprentice = this.apprenticeRepository.post(newApprentice);
            //String tokenApprentice = apprenticeRepository.authenticate(loginTextField.getText(), passwordTextField.getText());
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
