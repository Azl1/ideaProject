package com.abdullaevaziz.studentsfx.controllers;

import com.abdullaevaziz.studentsfx.App;
import com.abdullaevaziz.studentsfx.model.Student;
import com.abdullaevaziz.studentsfx.repository.StudentRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class MainController {

    @FXML
    public ListView<Student> listViewStudent;
    @FXML
    public Label labelStudents;

    private StudentRepository studentRepository = new StudentRepository();

    @FXML
    public void initialize() {
        try {
            this.listViewStudent.setItems(FXCollections.observableList(
                    new StudentRepository().get()));
            this.listViewStudent.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Student student = listViewStudent.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindow("auto.fxml", "Auto info", student);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void addButtonS(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addStudent.fxml", "Add student info", null);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButtonS(ActionEvent actionEvent) {
        try {
            Student selectedItem = this.listViewStudent.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select student", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("updateStudent.fxml", "Update student info", selectedItem);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void removeButtonS(ActionEvent actionEvent) {
        try {
            Student selectedItem = this.listViewStudent.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select student", Alert.AlertType.ERROR);
                return;
            }
            this.studentRepository.delete(selectedItem.getId());
            this.initialize();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonShowCars(ActionEvent actionEvent) {
        try {
            Student selectedItem = this.listViewStudent.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select a student", Alert.AlertType.ERROR);
                return;
            }
            App.openWindow("auto.fxml", "Auto info", selectedItem);
        } catch (IOException e) {
            App.showAlert("Info!", "Select a student!", Alert.AlertType.INFORMATION);
        }
    }

}
