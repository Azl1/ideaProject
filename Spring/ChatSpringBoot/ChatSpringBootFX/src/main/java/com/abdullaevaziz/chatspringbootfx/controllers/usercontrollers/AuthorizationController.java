package com.abdullaevaziz.chatspringbootfx.controllers.usercontrollers;

import com.abdullaevaziz.chatspringbootfx.App;
import com.abdullaevaziz.chatspringbootfx.model.User;
import com.abdullaevaziz.chatspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.chatspringbootfx.util.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AuthorizationController {

    @FXML
    public Label labelUsers;
    @FXML
    public Label labelUsers1;
    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);

    @FXML
    public void buttonUser(ActionEvent actionEvent) {
        try {
            String userLogin = loginTextField.getText();
            String password = passwordTextField.getText();

            UserRepository userRepository = new UserRepository();
            User user = userRepository.getByLoginAndPassword(userLogin, password);

            if (user == null) {
                App.showAlert("Error!", "Пользователь не зарегистрирован", Alert.AlertType.ERROR);
            }
            this.preferences.putLong(Constants.PREFERENCE_KEY_ID, user.getId());
            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, userLogin);
            this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, password);
            System.out.println("Id user auth " + user.getId());


            String res = "main.fxml";
                    App.openWindow("main.fxml", "Main chat", null);
                    App.closeWindow(actionEvent);



        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);

        } catch (
                IOException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        }
    }

    @FXML
    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("userRegistration.fxml", "Registration user info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
