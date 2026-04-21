package com.kirillkotov.javafxlect.controllers;

import com.kirillkotov.javafxlect.App;
import com.kirillkotov.javafxlect.model.Address;
import com.kirillkotov.javafxlect.model.User;
import com.kirillkotov.javafxlect.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainController {
    @FXML
    public TextField textFieldFIO;

    @FXML
    public TextField textFieldAge;

    @FXML
    public ComboBox<Address> comboBoxAddresses;
    @FXML
    public ListView<User> listViewUsers;

    @FXML
    public DatePicker datePickerBirthDay;

    @FXML
    public void initialize() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Moscow", "Win avenue"));
        addresses.add(new Address("Fethiye", "Ata turk sokak"));
        addresses.add(new Address("Kazan", "Nazarbayev avenue"));
        this.comboBoxAddresses.setItems(FXCollections.observableList(addresses));

        this.listViewUsers.setItems(FXCollections.observableList(
                new UserRepository().getUsers()));
    }

    @FXML
    public void buttonSave(ActionEvent actionEvent) {
        try {
            String fio = this.textFieldFIO.getText();
            int age = Integer.parseInt(this.textFieldAge.getText());
            Address address = this.comboBoxAddresses.getSelectionModel().getSelectedItem();
            if(address == null){
                App.showAlert("Error!", "Select address", Alert.AlertType.ERROR);
                return;
            }
            LocalDate localDate = this.datePickerBirthDay.getValue();
            if(localDate == null){
                App.showAlert("Error!", "Select birth day", Alert.AlertType.ERROR);
                return;
            }
            User user = new User(fio, age, address, localDate);
            new UserRepository().add(user);
            this.listViewUsers.getItems().add(user);

            App.showAlert("Info!", "User successfully added!", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            App.showAlert("Error!", "Incorrect format number", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonDelete(ActionEvent actionEvent) {
        User selectedItem = this.listViewUsers.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            App.showAlert("Error!", "Select user", Alert.AlertType.ERROR);
            return;
        }
        new UserRepository().delete(selectedItem);
        this.listViewUsers.getItems().remove(selectedItem);
        App.showAlert("Info!", "User successfully deleted!", Alert.AlertType.INFORMATION);
    }
}
