package com.abdullaevaziz.fencingschoolfx.controllers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolfx.model.Training;
import com.abdullaevaziz.fencingschoolfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainingRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ApprenticeController implements ControllerData<Apprentice> {

    @FXML
    public TextField textFiledFirstName;
    @FXML
    public TextField textFiledName;
    @FXML
    public TextField textFiledPatronymic;
    @FXML
    public TextField textFiledPhoneNumber;
    @FXML
    public ListView<Training> listTraining;
    private Apprentice apprentice;
    private ApprenticeRepository apprenticeRepository = new ApprenticeRepository();
    private TrainingRepository trainingRepository = new TrainingRepository();

    @Override
    public void initData(Apprentice value) {
        this.apprentice = value;
        this.textFiledFirstName.setText(this.apprentice.getSurname());
        this.textFiledName.setText(this.apprentice.getName());
        this.textFiledPatronymic.setText(this.apprentice.getPatronymic());
        this.textFiledPhoneNumber.setText(String.valueOf(this.apprentice.getPhoneNumber()));
        updateList();

    }

    public void updateList() {
        try {
            this.listTraining.setItems(FXCollections.observableList(new TrainingRepository().getByApprenticeId(this.apprentice.getId())));
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    @FXML
    public void buttonUpdateApprentice(ActionEvent actionEvent) {
        apprentice.setSurname(textFiledFirstName.getText());
        apprentice.setName(textFiledName.getText());
        apprentice.setPatronymic(textFiledPatronymic.getText());
        apprentice.setPhoneNumber(Long.parseLong(textFiledPhoneNumber.getText()));
        try {
            if (apprentice != null) {
                this.apprenticeRepository.put(apprentice);
                updateList();
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonRemoveApprentice(ActionEvent actionEvent) {
        try {
            this.apprenticeRepository.delete(apprentice.getId());
            App.showAlert("Info!", "Ученик удален!", Alert.AlertType.INFORMATION);
            textFiledFirstName.clear();
            textFiledName.clear();
            textFiledPatronymic.clear();
            textFiledPhoneNumber.clear();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonAddTraining(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("trainerAddTraining.fxml", "Training info", apprentice);
            initData(apprentice);
            updateList();
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
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
                initData(apprentice);
                updateList();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                App.showAlert("Error!", " Training с таким user id не существует!", Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

}
