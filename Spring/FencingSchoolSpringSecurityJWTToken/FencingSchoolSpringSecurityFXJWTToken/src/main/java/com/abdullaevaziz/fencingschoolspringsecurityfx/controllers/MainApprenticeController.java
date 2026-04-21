package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Training;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainingRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.UserRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import io.jsonwebtoken.Jwts;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Base64;
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
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private ApprenticeRepository apprenticeRepository;
    private TrainingRepository trainingRepository ;
    private TrainerRepository trainerRepository;
    private UserRepository userRepository ;
    private String newToken;

    public void initialize() {
         apprenticeRepository = new ApprenticeRepository(token);
         trainingRepository = new TrainingRepository(token);
         trainerRepository = new TrainerRepository(token);
         userRepository = new UserRepository(token);
        try {
            if (id == -1) {
                throw new IllegalArgumentException("User ID not found");
            }
            this.apprentice = new ApprenticeRepository(token).get(id);

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

            this.listTraining.setItems(FXCollections.observableList(new TrainingRepository(token).getByApprenticeId(apprentice.getId())));

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
        this.apprentice.setLogin(textFiledLogin.getText());
        this.apprentice.setSurname(textFiledFirstName.getText());
        this.apprentice.setName(textFiledName.getText());
        this.apprentice.setPatronymic(textFiledPatronymic.getText());
        this.apprentice.setPassword(textFiledPassword.getText());
        this.apprentice.setPhoneNumber(textFiledPhoneNumber.getText());


        try {
            if (apprentice != null) {
                this.apprenticeRepository.put(apprentice);
                long id = this.preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
                if (id == apprentice.getId()) {

                    //TODO если пароль не звездочки то послать запрос на получение нового токена и сохранить в преференсы
                    if (!textFiledPassword.getText().equals("***")) {

                        String userLogin = textFiledLogin.getText();
                        String password = textFiledPassword.getText();

                        UserRepository userRepository = new UserRepository();
                        newToken = userRepository.authenticate(userLogin, password);

                        this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, textFiledLogin.getText());
                        this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, password);
                        this.preferences.put(Constants.PREFERENCE_KEY_TOKEN, newToken);

                        this.apprenticeRepository = new ApprenticeRepository(newToken);
                        this.trainingRepository = new TrainingRepository(newToken);
                        this.trainerRepository = new TrainerRepository(newToken);

                    }
                }

                App.showAlert("Info!", "Ученик успешно обновлен!", Alert.AlertType.INFORMATION);
                  //initialize();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException ignored) {
            ignored.printStackTrace();
            App.showAlert("Error!", "Error contacting server!!!", Alert.AlertType.ERROR);
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
            //initialize();
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
