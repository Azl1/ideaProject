package com.abdullaevaziz.studentsspringbootdataclient.controllers;

import com.abdullaevaziz.studentsspringbootdataclient.App;
import com.abdullaevaziz.studentsspringbootdataclient.constants.Constants;
import com.abdullaevaziz.studentsspringbootdataclient.model.Student;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.StudentRepository;
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
import java.util.prefs.Preferences;


public class MainController {

    @FXML
    public ListView<Student> listViewStudent;
    @FXML
    public Label labelStudents;

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);

    private StudentRepository studentRepository = new StudentRepository(login, password);

    @FXML
    public void initialize() {
        try {
            this.listViewStudent.setItems(FXCollections.observableList(
                    new StudentRepository().getAll()));
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
    public void addButtonS(ActionEvent actionEvent) throws NoSuchFieldException, IllegalAccessException {
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

    @FXML
    public void buttonExit(ActionEvent actionEvent) {
        this.preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
        this.preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        App.closeWindow(actionEvent);
        try {
            App.openWindow("authorization.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
