package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Admin;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.User;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.AdminRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.UserRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Trainer;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AddTrainerApprenticeAdminController {

    @FXML
    public TextField textFiledSurname;
    @FXML
    public TextField textFiledName;
    @FXML
    public TextField textFiledPatronymic;
    @FXML
    public TextField textFieldParameter;
    @FXML
    public Label labelParameter;
    @FXML
    public Label labelEmail;
    @FXML
    public TextField textFieldEmail;
    @FXML
    public Label labelSalary;
    @FXML
    public RadioButton radioButApprentice;
    @FXML
    public RadioButton radioButTrainer;
    @FXML
    public RadioButton radioButAdmin;
    @FXML
    public TextField textFiledFirstLogin;
    @FXML
    public TextField textFiledPassword;
    @FXML
    public TextField textFieldSalary;
    private Admin admin;
    private Trainer trainer;
    private Apprentice apprentice;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    private TrainerRepository trainerRepository = new TrainerRepository(login, password);
    private ApprenticeRepository apprenticeRepository = new ApprenticeRepository(login, password);
    private AdminRepository adminRepository = new AdminRepository(login, password);


    @FXML
    public void radioButtonTrainer(ActionEvent actionEvent) {
        labelParameter.setText("Опыт");
        labelEmail.setVisible(true);
        textFieldEmail.setVisible(true);
        labelSalary.setVisible(false);
        textFieldSalary.setVisible(false);
    }

    @FXML
    public void radioButtonApprentice(ActionEvent actionEvent) {
        labelParameter.setText("Номер телефона");
        labelEmail.setVisible(false);
        textFieldEmail.setVisible(false);
        labelSalary.setVisible(false);
        textFieldSalary.setVisible(false);
    }

    @FXML
    public void radioButtonAdmin(ActionEvent actionEvent) {
        labelParameter.setText("Email");
        labelEmail.setVisible(false);
        textFieldEmail.setVisible(false);
        labelSalary.setVisible(true);
        textFieldSalary.setVisible(true);
        labelSalary.setLayoutX(141.0);
        labelSalary.setLayoutY(294.0);
        textFieldSalary.setLayoutX(254.0);
        textFieldSalary.setLayoutY(299.0);
    }

    @FXML
    public void buttonSave(ActionEvent actionEvent) throws IOException {
        try {
            String login = textFiledFirstLogin.getText();
            String surname = textFiledSurname.getText();
            String name = textFiledName.getText();
            String patronymic = textFiledPatronymic.getText();
            String parameter = textFieldParameter.getText();
            String password = textFiledPassword.getText();
            String email = textFieldEmail.getText();
            String salary = textFieldSalary.getText();


            if (surname.isEmpty() || name.isEmpty() || parameter.isEmpty()) {
                App.showAlert("Error!", "Заполните все обязательные поля!", Alert.AlertType.ERROR);
                return;
            }

            if (radioButTrainer.isSelected()) {
                trainer = new Trainer(login, surname, name, patronymic, password, Integer.parseInt(parameter), email);
                try {
                    trainerRepository.post(trainer);
                    App.showAlert("Info!", "Trainer добавлен!", Alert.AlertType.INFORMATION);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                }
            } else if (radioButApprentice.isSelected()) {
                apprentice = new Apprentice(login, surname, name, patronymic, password, parameter);
                try {
                    apprenticeRepository.post(apprentice);
                    App.showAlert("Info!", "Apprentice добавлен!", Alert.AlertType.INFORMATION);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                }

            } else if (radioButAdmin.isSelected()) {
                admin = new Admin(login, surname, name, patronymic, password, parameter, Double.parseDouble(salary));
                try {
                    adminRepository.post(admin);
                    App.showAlert("Info!", "Admin добавлен!", Alert.AlertType.INFORMATION);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                }
            }
            textFiledFirstLogin.clear();
            textFiledSurname.clear();
            textFiledName.clear();
            textFiledPatronymic.clear();
            textFieldParameter.clear();
            textFiledPassword.clear();
            textFieldEmail.clear();
            textFieldSalary.clear();

            App.closeWindow(actionEvent);
        } catch (NumberFormatException e) {
            App.showAlert("Error!", "Данные не правильно введены!", Alert.AlertType.ERROR);
        }
    }

    public void initialize() {
        labelSalary.setVisible(false);
        textFieldSalary.setVisible(false);
    }

    public void buttonExit(ActionEvent actionEvent) {

        try {
            User user = new UserRepository(login, password).get();

            if (user instanceof Admin) {
                admin = (Admin) user;
                App.openWindow("mainAdmin.fxml", "Admin info", null);
                App.closeWindow(actionEvent);
                return;
            }
            if (user instanceof Trainer) {
                trainer = (Trainer) user;
                App.openWindow("mainTrainer.fxml", "Trainer info", trainer);
                App.closeWindow(actionEvent);
                return;
            }
            if (user instanceof Apprentice) {
                apprentice = (Apprentice) user;
                App.openWindow("mainApprentice.fxml", "Apprentice info", apprentice);
                App.closeWindow(actionEvent);
                return;
            }

            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

