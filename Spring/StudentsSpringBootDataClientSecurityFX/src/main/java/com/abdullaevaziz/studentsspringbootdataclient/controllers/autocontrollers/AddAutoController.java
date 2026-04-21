package com.abdullaevaziz.studentsspringbootdataclient.controllers.autocontrollers;

import com.abdullaevaziz.studentsspringbootdataclient.App;
import com.abdullaevaziz.studentsspringbootdataclient.constants.Constants;
import com.abdullaevaziz.studentsspringbootdataclient.controllers.ControllerData;
import com.abdullaevaziz.studentsspringbootdataclient.model.Auto;
import com.abdullaevaziz.studentsspringbootdataclient.model.Student;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.AutoRepository;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

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

        String brand = brandTextField.getText();
        String powerText = (powerTextField.getText());
        String yearText = yearTextField.getText();

        if (brand.isEmpty() || powerText.isEmpty() || yearText.isEmpty()) {
            App.showAlert("Error!", "Поля не заполнены!", Alert.AlertType.ERROR);
            return;
        }

        int power = Integer.parseInt(powerTextField.getText());
        int year = Integer.parseInt(yearTextField.getText());

        Auto newAuto = new Auto(brand, power, year);

        try {
            this.autoRepository.post(newAuto, student.getId());
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
