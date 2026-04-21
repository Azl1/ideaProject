package com.abdullaevaziz.userfilesversionsspringbootfx.controllers.usercontrollers;

import com.abdullaevaziz.userfilesversionsspringbootfx.App;
import com.abdullaevaziz.userfilesversionsspringbootfx.model.User;
import com.abdullaevaziz.userfilesversionsspringbootfx.retrofit.UserRepository;
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
    public TextField fioTextField;
    private UserRepository userRepository = new UserRepository();

    @FXML
    public void addUser(ActionEvent actionEvent) {
        User newUser = new User(loginTextField.getText(), passwordTextField.getText(), fioTextField.getText());
        try {
            User user = this.userRepository.post(newUser);
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
