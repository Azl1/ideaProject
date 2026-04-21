package com.abdullaevaziz.cardfxspring.controllers.usercontroller;

import com.abdullaevaziz.cardfxspring.App;
import com.abdullaevaziz.cardfxspring.model.User;
import com.abdullaevaziz.cardfxspring.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddUserController {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField nameTextField;
    private UserRepository userRepository = new UserRepository();


    /**
     * 3. Registration – форма, позволяющая произвести регистрацию
     * нового пользователя в системе, после успешной регистрации
     * переход осуществляется на форму Authorization
     */
    @FXML
    public void addUser(ActionEvent actionEvent) {
            User addUser = new User(loginTextField.getText(), passwordTextField.getText(), nameTextField.getText());
        try {
                this.userRepository.add(addUser);
                App.closeWindow(actionEvent);
        }  catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

}
