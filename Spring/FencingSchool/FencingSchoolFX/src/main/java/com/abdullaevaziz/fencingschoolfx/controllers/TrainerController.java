package com.abdullaevaziz.fencingschoolfx.controllers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.model.Trainer;
import com.abdullaevaziz.fencingschoolfx.model.TrainerSchedule;
import com.abdullaevaziz.fencingschoolfx.model.TrainerScheduleItem;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainerScheduleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class TrainerController implements ControllerData<Trainer> {

    @FXML
    public TextField textFiledFirstName;
    @FXML
    public TextField textFiledName;
    @FXML
    public TextField textFiledPatronymic;
    @FXML
    public TextField textFiledExperience;
    @FXML
    public TableView<TrainerScheduleItem> scheduleTable;
    public TableColumn dayColumn;
    public TableColumn startTimeColumn;
    public TableColumn endTimeColumn;
    private Trainer trainer;
    private TrainerRepository trainerRepository = new TrainerRepository();
    private TrainerScheduleRepository trainerScheduleRepository = new TrainerScheduleRepository();
    private TrainerSchedule trainerSchedule;
    private ObservableList<TrainerScheduleItem> scheduleData = FXCollections.observableArrayList();

    @Override
    public void initData(Trainer value) throws NoSuchFieldException, IllegalAccessException, IOException {
        this.trainer = value;
        textFiledFirstName.setText(trainer.getSurname());
        textFiledName.setText(trainer.getName());
        textFiledPatronymic.setText(trainer.getPatronymic());
        textFiledExperience.setText(String.valueOf(trainer.getExperience()));

        //TODO по айди тренера из репозитория тренерскедуле получить объект тренер скедуле

        //TODO тут до точки его использовать


        TableColumn<TrainerScheduleItem, String> dayColumn = new TableColumn<>("День недели");
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("rusDay"));

        TableColumn<TrainerScheduleItem, String> startTimeColumn = new TableColumn<>("Время начала работы");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<TrainerScheduleItem, String> endTimeColumn = new TableColumn<>("Время окончания работы");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        updateList();

        this.scheduleTable.getColumns().setAll(dayColumn, startTimeColumn, endTimeColumn);
    }

    public void updateList() throws IOException, NoSuchFieldException, IllegalAccessException {
        this.trainerSchedule = trainerScheduleRepository.get(trainer.getId());
        System.out.println(trainerSchedule);
        ObservableList<TrainerScheduleItem> data = FXCollections.observableArrayList(trainerSchedule.get());
        this.scheduleTable.setItems(data);
    }

    @FXML
    public void buttonUpdateTrainer(ActionEvent actionEvent) {
        trainer.setSurname(textFiledFirstName.getText());
        trainer.setName(textFiledName.getText());
        trainer.setPatronymic(textFiledPatronymic.getText());
        trainer.setExperience(Integer.parseInt(textFiledExperience.getText()));
        try {
            if (trainer != null) {
                this.trainerRepository.put(trainer);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
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
            textFiledFirstName.clear();
            textFiledName.clear();
            textFiledPatronymic.clear();
            textFiledExperience.clear();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonAddRecord(ActionEvent actionEvent) {
        TrainerScheduleItem selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
        try {
            App.openWindowAndWait("scheduleAddRecord.fxml", "Schedule add info", trainer);
            ObservableList<TrainerScheduleItem> items = scheduleTable.getItems();
            items.setAll(selectedItem);
            initData(trainer);
            items.add(selectedItem);
            updateList();
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
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
            this.trainerScheduleRepository.delete(trainer.getId(), selectedItem.getEngDay());
            ObservableList<TrainerScheduleItem> items = scheduleTable.getItems();
            items.remove(selectedItem);
            initData(trainer);
            updateList();
            App.showAlert("Info!", "Расписание удалено!", Alert.AlertType.INFORMATION);
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
