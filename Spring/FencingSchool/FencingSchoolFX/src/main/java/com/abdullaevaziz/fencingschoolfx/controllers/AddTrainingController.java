package com.abdullaevaziz.fencingschoolfx.controllers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolfx.model.Trainer;
import com.abdullaevaziz.fencingschoolfx.model.Training;
import com.abdullaevaziz.fencingschoolfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddTrainingController implements ControllerData<Training> {

    @FXML
    public TextField textFiledFirstName;
    @FXML
    public TextField textFiledName;
    @FXML
    public TextField textFiledPatronymic;
    @FXML
    public TextField textFieldParameter;
    @FXML
    public Label labelParameter;
    @FXML
    public RadioButton radioButTrainer;
    @FXML
    public RadioButton radioButApprentice;
    private TrainerRepository trainerRepository = new TrainerRepository();
    private ApprenticeRepository apprenticeRepository = new ApprenticeRepository();


    @Override
    public void initData(Training value) {
    }

    @FXML
    public void radioButtonTrainer(ActionEvent actionEvent) {
        labelParameter.setText("Опыт");
    }

    @FXML
    public void radioButtonApprentice(ActionEvent actionEvent) {
        labelParameter.setText("Номер телефона");
    }

    @FXML
    public void buttonSave(ActionEvent actionEvent) throws IOException {
        try {
            String firstName = textFiledFirstName.getText();
            String name = textFiledName.getText();
            String patronymic = textFiledPatronymic.getText();
            String parameterText = textFieldParameter.getText();

            if (firstName.isEmpty() || name.isEmpty() || parameterText.isEmpty()) {
                App.showAlert("Error!", "Заполните все обязательные поля!", Alert.AlertType.ERROR);
                return;
            }

            int parameter;
            try {
                parameter = Integer.parseInt(parameterText);
            } catch (NumberFormatException e) {
                App.showAlert("Error!", "Параметр должен быть числом!", Alert.AlertType.ERROR);
                return;
            }

            if (radioButTrainer.isSelected()) {
                Trainer trainer = new Trainer(firstName, name, patronymic, parameter);
                try {
                    trainerRepository.post(trainer);
                    App.showAlert("Info!", "Trainer добавлен!", Alert.AlertType.INFORMATION);
                } catch (IllegalArgumentException e) {
                    App.showAlert("Error!", "Ошибка тренер повторно добавлен!", Alert.AlertType.ERROR);
                }
            } else {
                Apprentice apprentice = new Apprentice(firstName, name, patronymic, parameter);
                try {
                    apprenticeRepository.post(apprentice);
                    App.showAlert("Info!", "Apprentice добавлен!", Alert.AlertType.INFORMATION);
                } catch (IllegalArgumentException e) {
                    App.showAlert("Error!", "Ошибка ученик повторно добавлен!", Alert.AlertType.ERROR);
                }
            }
            textFiledFirstName.clear();
            textFiledName.clear();
            textFiledPatronymic.clear();
            textFieldParameter.clear();
            App.closeWindow(actionEvent);
        } catch (NumberFormatException e) {
            App.showAlert("Error!", "Данные не правильно введены!", Alert.AlertType.ERROR);
        }
    }
}

