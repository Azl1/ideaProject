package com.abdullaevaziz.quizspringbootfx.controllers.usercontrollers;

import com.abdullaevaziz.quizspringbootfx.App;
import com.abdullaevaziz.quizspringbootfx.model.User;
import com.abdullaevaziz.quizspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.quizspringbootfx.util.Constants;
import io.jsonwebtoken.Jwts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Base64;
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
            String token = userRepository.authenticate(userLogin, password);
            /*if (token == null){
                App.openWindow("auth.fxml", "Auth info", null);
                App.closeWindow(actionEvent);
                App.showAlert("Error!", "Token = null", Alert.AlertType.ERROR);
                return;
            }*/
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

            User user = new UserRepository(token).getUserId(id);

            String res = null;
            switch (role) {
                case "ROLE_ADMIN":
                    res = "choseUser.fxml";
                    App.openWindow("choseUser.fxml", "Chose user", null);
                    App.closeWindow(actionEvent);
                    break;
                case "ROLE_USER":
                    res = "main.fxml";
                    App.openWindow("main.fxml", "Main", null);
                    App.closeWindow(actionEvent);
                    break;
                default:
                    res = "auth.fxml";
            }


        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);

        } catch (
                IOException e) {
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
