package com.abdullaevaziz.controllers;

import com.abdullaevaziz.combobox.App;
import com.abdullaevaziz.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserInfoController implements ControllerData<User> {

    @FXML
    Label idLabel;
    @FXML
    TextField idTextField;
    @FXML
    Label nameLabel;
    @FXML
    TextField nameTextField;
    @FXML
    Label usernameLabel;
    @FXML
    TextField usernameTextField;
    @FXML
    Label emailLabel;
    @FXML
    TextField emailTextField;
    @FXML
    Label phoneLabel;
    @FXML
    TextField phoneTextField;
    @FXML
    Label websiteLabel;
    @FXML
    TextField websiteTextField;

    private User user;


    /**
     * 2. В новом окне, принять переданный объект,
     * сохранить в поле класса и отобразить в элементах
     * TextField каждое поле этого объекта по отдельности.
     * Другими словами, необходимо вывести всю информацию о переданном объекте
     * (кроме объектных полей) в различных текстовых полях
     */

    @Override
    public void initData(User value) {
        this.user = value;
        idTextField.setText(String.valueOf(value.getId()));
        nameTextField.setText(value.getName());
        usernameTextField.setText(value.getUsername());
        emailTextField.setText(value.getEmail());
        phoneTextField.setText(value.getPhone());
        websiteTextField.setText(value.getWebsite());
    }

    /**
     * 3. Основное окно должно дождаться закрытия второго,
     * и после закрытия в основном окне вывести сообщение, что просмотр объекта закончен
     */
    public void buttonClose(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
    }
}
