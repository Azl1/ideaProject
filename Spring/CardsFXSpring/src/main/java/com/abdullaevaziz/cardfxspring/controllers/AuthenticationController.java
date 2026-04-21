package com.abdullaevaziz.cardfxspring.controllers;

import com.abdullaevaziz.cardfxspring.App;
import com.abdullaevaziz.cardfxspring.constants.Constants;
import com.abdullaevaziz.cardfxspring.model.User;
import com.abdullaevaziz.cardfxspring.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AuthenticationController {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;

    private UserRepository userRepository = new UserRepository();

    private Preferences preferences = Preferences.userNodeForPackage(App.class);

    /**
     * 1. Program – главный класс приложения.
     * Осуществляет проверку на авторизованного ранее пользователя в системе,
     * если пользователь авторизован, то происходит переход на
     * главную форму приложения Main,
     * если нет, то осуществляется переход на Authorization
     * для проведения процедуры авторизации
     */
    @FXML
    public void buttonUser(ActionEvent actionEvent) {
        try {
            User user = userRepository.getUser(loginTextField.getText(), passwordTextField.getText());
            if(user == null){
                App.showAlert("Error!", "Пользователь отсутствует!", Alert.AlertType.ERROR);
                return;
            }

            //TODO в преференсы записать айдишник этого юзера
            this.preferences.putLong(Constants.PREFERENCE_USER_ID, user.getId());
            App.openWindow("main.fxml", "Category info", user);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", "Не верный логин или пароль!", Alert.AlertType.ERROR);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    /**
     * 2. Authorization – форма, производящая процедуру авторизации в системе.
     * Позволяет ввести логин и пароль и произвести вход. Так же есть возможность
     * из данной формы перейти на форму регистрации нового пользователя
     * Registration или же при успехе на Main
     */
    public void buttonRegUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addUser.fxml", "Category info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
