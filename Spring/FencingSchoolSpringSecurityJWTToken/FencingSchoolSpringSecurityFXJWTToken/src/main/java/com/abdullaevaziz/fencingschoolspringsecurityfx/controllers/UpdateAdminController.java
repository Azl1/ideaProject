package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Admin;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.*;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class UpdateAdminController implements ControllerData<Admin> {
   @FXML
    public TextField textFiledSurname;
    @FXML
    public TextField textFiledName;
    @FXML
    public TextField textFiledPatronymic;
    @FXML
    public Label labelEmail;
    @FXML
    public TextField textFieldEmail;
    @FXML
    public TextField textFiledFirstLogin;
    @FXML
    public TextField textFiledPassword;
    @FXML
    public Label labelSalary;
    @FXML
    public TextField textFieldSalary;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private String login = this.preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    private AdminRepository adminRepository = new AdminRepository(token);;
    private Admin admin;
    private String newToken;

    @Override
    public void initData(Admin value) throws IOException {
        String login = this.preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
        String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);

        admin = value;

        textFiledFirstLogin.setText(value.getLogin());
        textFiledSurname.setText(value.getSurname());
        textFiledName.setText(value.getName());
        textFiledPatronymic.setText(value.getPatronymic());
        textFiledPassword.setText("***");
        textFieldEmail.setText(value.getEmail());
        textFieldSalary.setText(String.valueOf(value.getSalary()));
    }

    @FXML
    public void buttonUpdate(ActionEvent actionEvent) {
        admin.setLogin(textFiledFirstLogin.getText());
        admin.setSurname(textFiledSurname.getText());
        admin.setName(textFiledName.getText());
        admin.setPatronymic(textFiledPatronymic.getText());
        admin.setPassword(textFiledPassword.getText());
        admin.setEmail(textFieldEmail.getText());
        admin.setSalary(Double.parseDouble(textFieldSalary.getText()));

        try {
            if (admin != null) {
                try {
                    adminRepository.put(admin);
                    long id = this.preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
                    if (id == admin.getId()) {
                        if (!textFiledPassword.getText().equals("***")) {

                            String userLogin = textFiledFirstLogin.getText();
                            String password = textFiledPassword.getText();

                            UserRepository userRepository = new UserRepository();
                            newToken = userRepository.authenticate(userLogin, password);

                            this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, textFiledFirstLogin.getText());
                            this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, password);
                            this.preferences.put(Constants.PREFERENCE_KEY_TOKEN, newToken);

                            this.adminRepository = new AdminRepository(newToken);
                        }
                    }
                    App.closeWindow(actionEvent);
                } catch (IllegalArgumentException e) {
                    App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

    }
}
