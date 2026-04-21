package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers.usercontrollers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Admin;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Trainer;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.User;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
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

            User user = new UserRepository(userLogin, password).get();
            if (user == null) {
                App.showAlert("Error!", "Пользователь отсутствует!", Alert.AlertType.ERROR);
                return;
            }

            this.preferences.putLong(Constants.PREFERENCE_KEY_ID, user.getId());
            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, userLogin);
            this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, password);

            if (user instanceof Admin) {
                App.openWindow("mainAdmin.fxml", "Admin info", null);
                App.closeWindow(actionEvent);
            }
            if (user instanceof Trainer) {
                App.openWindow("mainTrainer.fxml", "Trainer info", null);
                App.closeWindow(actionEvent);
            }
            if (user instanceof Apprentice) {
                App.openWindow("mainApprentice.fxml", "Apprentice info", null);
                App.closeWindow(actionEvent);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("apprenticeRegistration.fxml", "Registration user info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
