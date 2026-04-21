package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Training;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainingRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class MainApprenticeController {

    @FXML
    public TextField textFiledLogin;
    @FXML
    public TextField textFiledFirstName;
    @FXML
    public TextField textFiledName;
    @FXML
    public TextField textFiledPatronymic;
    @FXML
    public TextField textFiledPassword;
    @FXML
    public TextField textFiledPhoneNumber;
    @FXML
    public ListView<Training> listTraining;
    @FXML
    public TextField textFiledID;

    private Apprentice apprentice;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String login = this.preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    private long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
    private ApprenticeRepository apprenticeRepository = new ApprenticeRepository(login, password);
    private TrainingRepository trainingRepository;
    private TrainerRepository trainerRepository;


    public void initialize() {
        try {
            if (id == -1) {
                throw new IllegalArgumentException("User ID not found");
            }
            this.apprentice = new ApprenticeRepository(login, password).get(id);
            this.trainingRepository = new TrainingRepository(login, password);
            this.trainerRepository = new TrainerRepository(login, password);

            if (apprentice == null) {
                App.showAlert("Ошибка", "Ученик не найден", Alert.AlertType.ERROR);
                App.openWindow("auth.fxml", "Авторизация", null);
                return;
            }
            this.textFiledID.setText(String.valueOf(this.apprentice.getId()));
            this.textFiledLogin.setText(this.apprentice.getLogin());
            this.textFiledFirstName.setText(this.apprentice.getSurname());
            this.textFiledName.setText(this.apprentice.getName());
            this.textFiledPatronymic.setText(this.apprentice.getPatronymic());
            this.textFiledPassword.setText("***");
            this.textFiledPhoneNumber.setText(String.valueOf(this.apprentice.getPhoneNumber()));

            this.listTraining.setItems(FXCollections.observableList(new TrainingRepository(login, password).getByApprenticeId(apprentice.getId())));

        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonUpdateApprentice(ActionEvent actionEvent) {
        apprentice.setLogin(textFiledLogin.getText());
        apprentice.setSurname(textFiledFirstName.getText());
        apprentice.setName(textFiledName.getText());
        apprentice.setPatronymic(textFiledPatronymic.getText());
        apprentice.setPassword(textFiledPassword.getText());
        apprentice.setPhoneNumber(textFiledPhoneNumber.getText());
        try {
            if (apprentice != null) {
                this.apprenticeRepository.put(apprentice);
                long id = this.preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
                if (id == apprentice.getId()) {
                    preferences.put(Constants.PREFERENCE_KEY_LOGIN, apprentice.getLogin());
                    preferences.put(Constants.PREFERENCE_KEY_PASSWORD, apprentice.getPassword());
                }
                App.showAlert("Info!", "Ученик успешно обновлен!", Alert.AlertType.INFORMATION);
                initialize();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonRemoveApprentice(ActionEvent actionEvent) {
        try {
            this.apprenticeRepository.delete(apprentice.getId());
            App.showAlert("Info!", "Ученик удален!", Alert.AlertType.INFORMATION);
            App.openWindow("auth.fxml", "Authorization info", null);
            textFiledLogin.clear();
            textFiledFirstName.clear();
            textFiledName.clear();
            textFiledPatronymic.clear();
            textFiledPassword.clear();
            textFiledPhoneNumber.clear();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonAddTraining(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addTraining.fxml", "Add training info", apprentice);
            initialize();
        } catch (IOException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRemoveTraining(ActionEvent actionEvent) {
        try {
            Training selectedItem = this.listTraining.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select training", Alert.AlertType.ERROR);
                return;
            }
            try {
                this.trainingRepository.delete(selectedItem.getId());
                this.listTraining.getItems().remove(selectedItem);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    public void buttonExit(ActionEvent actionEvent) {
        preferences.remove(Constants.PREFERENCE_KEY_ID);
        preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
        preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
