package com.abdullaevaziz.cardfxspring.controllers.usercontroller;

import com.abdullaevaziz.cardfxspring.App;
import com.abdullaevaziz.cardfxspring.controllers.ControllerData;
import com.abdullaevaziz.cardfxspring.model.User;
import com.abdullaevaziz.cardfxspring.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateUser implements ControllerData<User> {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField nameTextField;

    private UserRepository userRepository = new UserRepository();
    private User user;

    @Override
    public void initData(User value) throws IOException {
        this.user = value;
        loginTextField.setText(value.getLogin());
        passwordTextField.setText(value.getPassword());
        nameTextField.setText(value.getName());
    }

    @FXML
    public void updateUser(ActionEvent actionEvent) {
        user.setLogin(loginTextField.getText());
        user.setPassword(passwordTextField.getText());
        user.setName(nameTextField.getText());
        try {
            if (user != null) {
                this.userRepository.update(user);
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

}
