package com.abdullaevaziz.studentsfx.controllers.studentcontrollers;

import com.abdullaevaziz.studentsfx.App;
import com.abdullaevaziz.studentsfx.model.Student;
import com.abdullaevaziz.studentsfx.repository.StudentRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AddStudentController {


    @FXML
    public Label fioLabel;
    public TextField fioTextField;
    @FXML
    public Label AgeLabel;
    @FXML
    public TextField ageTextField;
    @FXML
    public Label numberLabel;
    @FXML
    public TextField numberTextField;
    @FXML
    public Label SalaryLabel;
    @FXML
    public TextField salaryTextField;
    private StudentRepository studentRepository = new StudentRepository();
    @FXML
    public void addStudent(ActionEvent actionEvent) {
        Student newStudent = new Student(fioTextField.getText(), Integer.parseInt(ageTextField.getText()),
                Integer.parseInt(numberTextField.getText()), Integer.parseInt(salaryTextField.getText()));
        try {
            studentRepository.add(newStudent);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

    }


}
