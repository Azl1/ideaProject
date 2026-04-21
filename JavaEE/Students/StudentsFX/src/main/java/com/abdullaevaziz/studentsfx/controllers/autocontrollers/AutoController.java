package com.abdullaevaziz.studentsfx.controllers.autocontrollers;

import com.abdullaevaziz.studentsfx.App;
import com.abdullaevaziz.studentsfx.controllers.ControllerData;
import com.abdullaevaziz.studentsfx.model.Auto;
import com.abdullaevaziz.studentsfx.model.Student;
import com.abdullaevaziz.studentsfx.repository.AutoRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class AutoController implements ControllerData<Student> {
    @FXML
    public Label labelAuto;
    private Student student;
    @FXML
    public ListView<Auto> listViewAuto;

    private AutoRepository autoRepository = new AutoRepository();


    @FXML
    public void addButtonA(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addAuto.fxml", "Add auto info", this.student);
            initData(student);
            App.showAlert("Info!", "Auto successfully add!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButtonA(ActionEvent actionEvent) {
        Auto selectedItem = this.listViewAuto.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem == null) {
                App.showAlert("Error!", "Select auto", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("updateAuto.fxml", "Update auto info", selectedItem);
            initData(student);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void removeButtonA(ActionEvent actionEvent) throws IOException {
        Auto selectedItem = this.listViewAuto.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            App.showAlert("Error!", "Select student", Alert.AlertType.ERROR);
            return;
        }
        this.listViewAuto.getItems().remove(selectedItem);
        this.autoRepository.delete(selectedItem.getId());
        App.showAlert("Info!", "Auto successfully deleted!", Alert.AlertType.INFORMATION);
    }


    @Override
    public void initData(Student value) {
        try {
            this.student = value;
            this.listViewAuto.setItems(FXCollections.observableList(
                    new AutoRepository().getListAuto(student.getId())));
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
