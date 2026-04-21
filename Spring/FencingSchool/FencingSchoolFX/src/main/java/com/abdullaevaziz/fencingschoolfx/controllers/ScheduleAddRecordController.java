package com.abdullaevaziz.fencingschoolfx.controllers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.constants.Constants;
import com.abdullaevaziz.fencingschoolfx.model.Trainer;
import com.abdullaevaziz.fencingschoolfx.model.TrainerSchedule;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainerScheduleRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleAddRecordController implements ControllerData<Trainer> {

    @FXML
    public ComboBox<String> dayOfTheWeekBox;
    @FXML
    public ComboBox<LocalTime> timeStartBox;
    @FXML
    public ComboBox<LocalTime> timeEndBox;
    private TrainerScheduleRepository trainerScheduleRepository = new TrainerScheduleRepository();
    private Trainer trainer;

    @FXML
    public void addRecordButton(ActionEvent actionEvent) {

        String russianDay = this.dayOfTheWeekBox.getValue();
        String englishDay = Constants.DAYS.get(russianDay);
        LocalTime selectedStartTime = this.timeStartBox.getValue();
        LocalTime selectedEndTime = this.timeEndBox.getValue();

        if (englishDay == null || selectedStartTime == null || selectedEndTime == null) {
            App.showAlert("Error!", "Fill in all fields!", Alert.AlertType.ERROR);
            return;
        }

        if (selectedEndTime.isBefore(selectedStartTime)) {
            App.showAlert("Error!", "The time is set incorrectly!", Alert.AlertType.ERROR);
            return;
        }

        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleRepository.post(trainer.getId(), englishDay,
                    selectedStartTime, selectedEndTime);
            App.showAlert("Info!", "Schedule added!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void initData(Trainer value) {
        this.trainer = value;
    }

    @FXML
    public void initialize() {
        ArrayList<String> list = new ArrayList<>(Constants.DAYS.keySet());
        this.dayOfTheWeekBox.setItems(FXCollections.observableList(list));

        this.timeStartBox.setItems(FXCollections.observableList(new ArrayList<>()));
        this.timeEndBox.setItems(FXCollections.observableList(new ArrayList<>()));

        LocalTime start = LocalTime.parse("08:00");
        LocalTime end = LocalTime.parse("22:00");

        for (LocalTime i = start; i.isBefore(end); i = i.plusMinutes(30)) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String timeStart = i.format(dateTimeFormatter);
            String timeEnd = i.format(dateTimeFormatter);
            this.timeStartBox.getItems().add(LocalTime.parse(timeStart));
            this.timeEndBox.getItems().add(LocalTime.parse(timeEnd));
        }
    }

}
