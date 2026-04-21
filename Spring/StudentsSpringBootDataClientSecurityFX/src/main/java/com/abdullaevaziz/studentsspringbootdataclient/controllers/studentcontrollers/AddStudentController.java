package com.abdullaevaziz.studentsspringbootdataclient.controllers.studentcontrollers;

import com.abdullaevaziz.studentsspringbootdataclient.App;
import com.abdullaevaziz.studentsspringbootdataclient.model.Student;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.StudentRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AddStudentController {


    @FXML
    public Label fioLabel;
    @FXML
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

        String fio = fioTextField.getText();
        String ageText = ageTextField.getText();
        String numText = numberTextField.getText();
        String salaryText = salaryTextField.getText();

        if (fio.isEmpty() || ageText.isEmpty() || numText.isEmpty() || salaryText.isEmpty()){
            App.showAlert("Error!", "Полня не заполнены!", Alert.AlertType.ERROR);
            return;
        }

        int age = Integer.parseInt(ageTextField.getText());
        int num = Integer.parseInt(numberTextField.getText());
        double salary = Double.parseDouble(salaryTextField.getText());

        Student newStudent = new Student(fio, age, num, salary);


        try {
            studentRepository.post(newStudent);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

    }


}
