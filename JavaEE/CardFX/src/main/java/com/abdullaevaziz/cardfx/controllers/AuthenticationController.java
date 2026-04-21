package com.abdullaevaziz.cardfx.controllers;

import com.abdullaevaziz.cardfx.App;
import com.abdullaevaziz.cardfx.model.Category;
import com.abdullaevaziz.cardfx.model.User;
import com.abdullaevaziz.cardfx.repository.CategoryRepository;
import com.abdullaevaziz.cardfx.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthenticationController {

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;

    private User user;
    private UserRepository userRepository = new UserRepository();

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
            this.user = userRepository.getUser(loginTextField.getText(), passwordTextField.getText());
            if(user == null){
                App.showAlert("Error!", "Пользователь отсутсвует!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("main.fxml", "Category info", user);
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
