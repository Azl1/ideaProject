package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainingRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Trainer;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.TrainerSchedule;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.TrainerScheduleItem;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainerScheduleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

public class MainTrainerController {

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
    public TextField textFiledExperience;
    @FXML
    public Label EmailLabel;
    @FXML
    public TextField textFiledEmail;
    @FXML
    public TextField textFiledID;


    private Trainer trainer;
    @FXML
    public TableView<TrainerScheduleItem> scheduleTable;
    public TableColumn dayColumn;
    public TableColumn startTimeColumn;
    public TableColumn endTimeColumn;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private TrainerRepository trainerRepository;
    private TrainerScheduleRepository trainerScheduleRepository;
    private TrainerSchedule trainerSchedule;
    private ObservableList<TrainerScheduleItem> scheduleData;


    public void initialize(){
        try {
            String login = this.preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
            String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
            long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
            if (id == -1) {
                throw new IllegalArgumentException("User ID not found");
            }

            trainerRepository =new  TrainerRepository(login, password);
            trainerScheduleRepository = new TrainerScheduleRepository(login, password);

            trainer = trainerRepository.getTrainerId(id);
            if (trainer == null) {
                throw new IllegalArgumentException("Trainer not found");
            }
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
        textFiledID.setText(String.valueOf(trainer.getId()));
        textFiledLogin.setText(trainer.getLogin());
        textFiledFirstName.setText(trainer.getSurname());
        textFiledName.setText(trainer.getName());
        textFiledPatronymic.setText(trainer.getPatronymic());
        this.textFiledPassword.setText("***");
        textFiledExperience.setText(String.valueOf(trainer.getExperience()));
        textFiledEmail.setText(trainer.getEmail());


        TableColumn<TrainerScheduleItem, String> dayColumn = new TableColumn<>("День недели");
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("rusDay"));

        TableColumn<TrainerScheduleItem, String> startTimeColumn = new TableColumn<>("Время начала работы");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<TrainerScheduleItem, String> endTimeColumn = new TableColumn<>("Время окончания работы");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        this.scheduleTable.getColumns().setAll(dayColumn, startTimeColumn, endTimeColumn);

        try {
            this.trainerSchedule = trainerScheduleRepository.get(trainer.getId());
            ObservableList<TrainerScheduleItem> data = null;
            try {
                data = FXCollections.observableArrayList(trainerSchedule.get());
            } catch (NoSuchFieldException e) {
               e.printStackTrace();
            } catch (IllegalAccessException e) {
               e.printStackTrace();
            }
            this.scheduleTable.setItems(data);
        } catch (IOException e) {
           e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void buttonUpdateTrainer(ActionEvent actionEvent) {
        trainer.setLogin(textFiledLogin.getText());
        trainer.setSurname(textFiledFirstName.getText());
        trainer.setName(textFiledName.getText());
        trainer.setPatronymic(textFiledPatronymic.getText());
        trainer.setPassword(textFiledPassword.getText());
        trainer.setExperience(Integer.parseInt(textFiledExperience.getText()));
        trainer.setEmail(textFiledEmail.getText());
        try {
            if (trainer != null) {
                this.trainerRepository.put(trainer);
                long id = this.preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
                if (id == trainer.getId()) {
                    preferences.put(Constants.PREFERENCE_KEY_LOGIN, trainer.getLogin());
                    preferences.put(Constants.PREFERENCE_KEY_PASSWORD, trainer.getPassword());
                }
                App.showAlert("Info!", "Тренер успешно обновлен!", Alert.AlertType.INFORMATION);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonRemoveTrainer(ActionEvent actionEvent) {
        try {
            this.trainerRepository.delete(trainer.getId());
            App.showAlert("Info!", "Тренер удален!", Alert.AlertType.INFORMATION);
            App.openWindow("auth.fxml", "Authorization info", null);
            textFiledLogin.clear();
            textFiledFirstName.clear();
            textFiledName.clear();
            textFiledPatronymic.clear();
            textFiledPassword.clear();
            textFiledExperience.clear();
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonAddRecord(ActionEvent actionEvent) {
        TrainerScheduleItem selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
        try {
            App.openWindowAndWait("scheduleAddRecord.fxml", "Add Record info", trainer);

            ObservableList<TrainerScheduleItem> items = scheduleTable.getItems();
            items.setAll(selectedItem);
            items.add(selectedItem);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRemoveRecord(ActionEvent actionEvent) {
        TrainerScheduleItem selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            App.showAlert("Ошибка", "Выберите запись для удаления", Alert.AlertType.ERROR);
            return;
        }
        try {
            this.trainerSchedule = trainerScheduleRepository.delete(trainer.getId(), selectedItem.getEngDay());
            ObservableList<TrainerScheduleItem> items = scheduleTable.getItems();
            items.remove(selectedItem);
            App.showAlert("Info!", "Расписание удалено!", Alert.AlertType.INFORMATION);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
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
