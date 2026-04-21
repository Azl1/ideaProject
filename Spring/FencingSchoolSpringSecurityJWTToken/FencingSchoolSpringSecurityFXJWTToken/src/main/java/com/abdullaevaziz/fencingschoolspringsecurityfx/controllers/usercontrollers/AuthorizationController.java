package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers.usercontrollers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.UserRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
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
            if (token == null){
                App.openWindow("auth.fxml", "Auth info", null);
                App.closeWindow(actionEvent);
                  App.showAlert("Error!", "Token = null", Alert.AlertType.ERROR);
                return;
            }
            System.out.println("TOKEN: " + token);

            String secretKey = "jwtappsecret";
            String username = (String) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("username");
            String role = (String) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("role");
            int id = (Integer) Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes())).parseClaimsJws(token).getBody().get("id");

            System.out.println(username);
            System.out.println(role);
            System.out.println(id);


            this.preferences.putLong(Constants.PREFERENCE_KEY_ID, id);
            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, username);
            this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, password);
            this.preferences.put(Constants.PREFERENCE_KEY_TOKEN, token);

            String res = null;
            switch (role){
                case "ROLE_ADMIN":
                    res = "mainAdmin.fxml";
                    App.openWindow("mainAdmin.fxml", "Admin info", null);
                    App.closeWindow(actionEvent);
                    break;
                case "ROLE_TRAINER":
                    res = "mainTrainer.fxml";
                    App.openWindow("mainTrainer.fxml", "Trainer info", null);
                    App.closeWindow(actionEvent);
                    break;
                case "ROLE_APPRENTICE":
                    res = "mainApprentice.fxml";
                    App.openWindow("mainApprentice.fxml", "Apprentice info", null);
                    App.closeWindow(actionEvent);
                    break;
                default:
                    res = "auth.fxml";
            }

            /*if (role.equals("Admin")) {
                App.openWindow("mainAdmin.fxml", "Admin info", null);
                App.closeWindow(actionEvent);
            }
            if (role.equals("Trainer")) {
                App.openWindow("mainTrainer.fxml", "Trainer info", null);
                App.closeWindow(actionEvent);
            }
            if (role.equals("Apprentice")) {
                App.openWindow("mainApprentice.fxml", "Apprentice info", null);
                App.closeWindow(actionEvent);
            }*/


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
