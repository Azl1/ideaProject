package com.abdullaevaziz.fileuploaderspringbootfx.controllers.usercontrollers;

import com.abdullaevaziz.fileuploaderspringbootfx.App;
import com.abdullaevaziz.fileuploaderspringbootfx.model.User;
import com.abdullaevaziz.fileuploaderspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.fileuploaderspringbootfx.util.Constants;
import com.abdullaevaziz.fileuploaderspringbootfx.util.Util;
import io.jsonwebtoken.Jwts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Base64;
import java.util.prefs.Preferences;

public class AuthorizationController {

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
            String token = userRepository.authenticate(userLogin, password);

            System.out.println("TOKEN: " + token);

            String username = Util.getUsername(token);
            String role = Util.getRole(token);
            long id = Util.getId(token);

            this.preferences.putLong(Constants.PREFERENCE_KEY_ID, id);
            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, username);
            this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, password);
            this.preferences.put(Constants.PREFERENCE_KEY_TOKEN, token);

            switch (role) {
                case "ROLE_ADMIN" -> {
                    App.openWindow("choseUser.fxml", "Chose user", null);
                    App.closeWindow(actionEvent);
                }
                case "ROLE_USER" -> {
                    App.openWindow("main.fxml", "Main", null);
                    App.closeWindow(actionEvent);
                }
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
        } catch (IOException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
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
