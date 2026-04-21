package com.abdullaevaziz.studentsfx.controllers.autocontrollers;

import com.abdullaevaziz.studentsfx.App;
import com.abdullaevaziz.studentsfx.controllers.ControllerData;
import com.abdullaevaziz.studentsfx.model.Auto;
import com.abdullaevaziz.studentsfx.model.Student;
import com.abdullaevaziz.studentsfx.repository.AutoRepository;
import com.abdullaevaziz.studentsfx.repository.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddAutoController implements ControllerData<Student> {
    @FXML
    public Label idAutoLabel;
    @FXML
    public TextField idAutoTextField;
    @FXML
    public Label powerLabel;
    @FXML
    public TextField powerTextField;
    @FXML
    public Label brandLabel;
    @FXML
    public TextField brandTextField;
    @FXML
    public Label yearLabel;
    @FXML
    public TextField yearTextField;
    @FXML
    public Label idLabel;
    @FXML
    public TextField idStudentTextField;
    private AutoRepository autoRepository = new AutoRepository();
    @FXML
    public ListView<Auto> listViewAuto;
    private Student student;

    @FXML
    public void initData(Student valStudent) {
        this.student = valStudent;
        idStudentTextField.setText(String.valueOf(student.getId()));
    }

    @FXML
    public void addAuto(ActionEvent actionEvent) {
        Auto newAuto = new Auto(brandTextField.getText(),
                Integer.parseInt(powerTextField.getText()),
                Integer.parseInt(yearTextField.getText()),
                student.getId());
        try {
            this.autoRepository.add(newAuto);
            App.closeWindow(actionEvent);
        }
        catch (IllegalArgumentException e){
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
        catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
