package com.abdullaevaziz.quizspringbootfx.controllers.usercontrollers;

import com.abdullaevaziz.quizspringbootfx.App;
import com.abdullaevaziz.quizspringbootfx.model.User;
import com.abdullaevaziz.quizspringbootfx.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController {


    @FXML
    public TextField loginTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField passwordTextField;


    private UserRepository userRepository = new UserRepository();

    @FXML
    public void addUser(ActionEvent actionEvent) {

        User newUser = new User(loginTextField.getText(),
                nameTextField.getText(), passwordTextField.getText());
        try {

            User user = this.userRepository.postUser(newUser);
            System.out.println(user);

            //String tokenUser = userRepository.authenticate(loginTextField.getText(), passwordTextField.getText());
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
