package com.abdullaevaziz.fencingschoolfx.controllers.usercontrollers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.constants.Constants;
import com.abdullaevaziz.fencingschoolfx.model.User;
import com.abdullaevaziz.fencingschoolfx.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AuthorizationController {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    private UserRepository userRepository = new UserRepository();
    private Preferences preferences = Preferences.userNodeForPackage(App.class);

    @FXML
    public void buttonUser(ActionEvent actionEvent) {
        try {
            User user = this.userRepository.getByLoginAndPassword(loginTextField.getText(), passwordTextField.getText());
            if (user == null){
                App.showAlert("Error!", "Пользователь отсутствует!", Alert.AlertType.ERROR);
                return;
            }

            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, user.getLogin());
            this.preferences.putLong(Constants.PREFERENCE_KEY_ID, user.getId());
            App.openWindow("main.fxml", "Training info", user);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Не верный логин или пароль!", Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("userRegistration.fxml", "Registration user info", null);
        } catch (IOException | NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
