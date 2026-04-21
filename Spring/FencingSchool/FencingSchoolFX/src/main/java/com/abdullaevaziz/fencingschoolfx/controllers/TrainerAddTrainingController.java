package com.abdullaevaziz.fencingschoolfx.controllers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolfx.model.Trainer;
import com.abdullaevaziz.fencingschoolfx.model.TrainerSchedule;
import com.abdullaevaziz.fencingschoolfx.model.Training;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainerScheduleRepository;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainingRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.abdullaevaziz.fencingschoolfx.util.Util.isOverlapping;

public class TrainerAddTrainingController implements ControllerData<Apprentice> {
    @FXML
    public ComboBox<Trainer> trainerListBox;
    @FXML
    public DatePicker datePickerDay;
    @FXML
    public ComboBox<LocalTime> timeBox;
    @FXML
    public TextField hallNumberTextField;
    @FXML
    public Label hallNumberLabel;
    @FXML
    public Button buttonSave;

    private Training training;
    private Apprentice apprentice;
    private TrainingRepository trainingRepository = new TrainingRepository();
    private TrainerRepository trainerRepository = new TrainerRepository();
    private TrainerScheduleRepository trainerScheduleRepository = new TrainerScheduleRepository();


    @FXML
    public void addTrainingButton(ActionEvent actionEvent) throws IOException {

        Trainer selectedTrainer = trainerListBox.getValue();
        LocalDate date = this.datePickerDay.getValue();
        LocalTime selectedTime = this.timeBox.getValue();
        String numberGymOfTraining = this.hallNumberTextField.getText();


        if (selectedTrainer == null) {
            App.showAlert("Error!", "Select trainer!", Alert.AlertType.ERROR);
            return;
        }

        if (date == null) {
            App.showAlert("Error!", "Select day training!", Alert.AlertType.ERROR);
            return;
        }

        if (selectedTime == null) {
            App.showAlert("Error!", "Select time training!", Alert.AlertType.ERROR);
            return;
        }

        if (numberGymOfTraining.isEmpty()) {
            App.showAlert("Error!", "Select hall number training!", Alert.AlertType.ERROR);
            return;
        }
        this.training = new Training(Integer.parseInt(numberGymOfTraining), date, selectedTime);
        try {
            this.trainingRepository.post(selectedTrainer.getId(), apprentice.getId(), training);
            App.showAlert("Info!", "Training added!", Alert.AlertType.INFORMATION);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);

        }
    }


    @Override
    public void initData(Apprentice value) {
        apprentice = value;
    }

    @FXML
    public void initialize() {
        try {
            this.trainerListBox.setItems(FXCollections.observableList(new TrainerRepository().getAll()));

            LocalTime start = LocalTime.parse("08:00");
            LocalTime end = LocalTime.parse("22:00");

            for (LocalTime i = start; i.isBefore(end); i = i.plusMinutes(30)) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                String time = i.format(dateTimeFormatter);
                this.timeBox.getItems().add(LocalTime.parse(time));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseATrainer(ActionEvent actionEvent) {
        datePickerDay.setVisible(true);

        datePickerDay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                timeBox.setVisible(true);
                hallNumberTextField.setVisible(true);
                hallNumberLabel.setVisible(true);
                buttonSave.setVisible(true);

            }
        });

        try {
            Trainer selectedTrainer = trainerListBox.getValue();
            TrainerSchedule trainerSchedule = trainerScheduleRepository.get(selectedTrainer.getId());

            datePickerDay.setDayCellFactory(param -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);

                    String day = date.getDayOfWeek().toString().toLowerCase();

                    setDisable(empty || date.compareTo(LocalDate.now()) < 0 || !trainerSchedule.isWorkMan(day));
                    //
                    if (!empty  && !trainerSchedule.isWorkMan(day)) {
                        this.setStyle("-fx-background-color: #ffb6b6;");
                    }
                }
            });

        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }


    }
}
