package com.abdullaevaziz.studentsspringbootdataclient.controllers.studentcontrollers;

import com.abdullaevaziz.studentsspringbootdataclient.App;
import com.abdullaevaziz.studentsspringbootdataclient.constants.Constants;
import com.abdullaevaziz.studentsspringbootdataclient.controllers.ControllerData;
import com.abdullaevaziz.studentsspringbootdataclient.model.Student;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.StudentRepository;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

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
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    private StudentRepository studentRepository = new StudentRepository(login, password);
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
        String id = idTextField.getText();
        String fio = fioTextField.getText();
        String age = ageTextField.getText();
        String number = numberTextField.getText();
        String salary = salaryTextField.getText();
        if (id.isEmpty() || fio.isEmpty() || age.isEmpty() || number.isEmpty() || salary.isEmpty()){
            App.showAlert("Error!", "Не все поля заполнены!", Alert.AlertType.ERROR);
            return;
        }


        student.setFio(fioTextField.getText());
        student.setAge(Integer.parseInt(ageTextField.getText()));
        student.setNum(Integer.parseInt(numberTextField.getText()));
        student.setSalary(Double.parseDouble(salaryTextField.getText()));

        try {
            if (student != null) {
                UserRepository userRepository = new UserRepository(login, password);
                studentRepository.put(student);
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

    }
}
