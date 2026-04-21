package com.abdullaevaziz.userfilesservletsfx.controllers;

import com.abdullaevaziz.userfilesservletsfx.App;
import com.abdullaevaziz.userfilesservletsfx.model.User;
import com.abdullaevaziz.userfilesservletsfx.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddUserController {

    @FXML
    public TextField fioTextField;
    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;

    private User user;
    private UserRepository userRepository = new UserRepository();

    @FXML
    public void addUser(ActionEvent actionEvent) {
        try {
            User addUser = new User(loginTextField.getText(), passwordTextField.getText(), fioTextField.getText());
            if(addUser == null){
                App.showAlert("Error!", "Данные не введены!", Alert.AlertType.ERROR);
                return;
            }
            this.userRepository.add(addUser);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error", "Такой user уже существует в системе! (FX)", Alert.AlertType.ERROR );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
