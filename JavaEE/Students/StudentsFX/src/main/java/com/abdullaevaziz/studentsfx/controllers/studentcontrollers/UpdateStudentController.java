package com.abdullaevaziz.studentsfx.controllers.studentcontrollers;

import com.abdullaevaziz.studentsfx.App;
import com.abdullaevaziz.studentsfx.controllers.ControllerData;
import com.abdullaevaziz.studentsfx.model.Student;
import com.abdullaevaziz.studentsfx.repository.StudentRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateStudentController implements ControllerData<Student> {
    @FXML
    public Label idLabel;
    @FXML
    public TextField idTextField;
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
    private Student student;
    private StudentRepository studentRepository = new StudentRepository();

    @Override
    public void initData(Student value) {
        this.student = value;
        idTextField.setText(String.valueOf(value.getId()));
        fioTextField.setText(value.getFio());
        ageTextField.setText(String.valueOf(value.getAge()));
        numberTextField.setText(String.valueOf(value.getNum()));
        salaryTextField.setText(String.valueOf(value.getSalary()));

    }


    public void closeButton(ActionEvent actionEvent) {
        student.setFio(fioTextField.getText());
        student.setAge(Integer.parseInt(ageTextField.getText()));
        student.setNum(Integer.parseInt(numberTextField.getText()));
        student.setSalary(Double.parseDouble(salaryTextField.getText()));

        try {
            if (student != null) {
                studentRepository.update(student);
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

    }
}
