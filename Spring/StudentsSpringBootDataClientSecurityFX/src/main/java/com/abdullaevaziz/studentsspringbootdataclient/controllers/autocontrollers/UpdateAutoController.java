package com.abdullaevaziz.studentsspringbootdataclient.controllers.autocontrollers;

import com.abdullaevaziz.studentsspringbootdataclient.App;
import com.abdullaevaziz.studentsspringbootdataclient.constants.Constants;
import com.abdullaevaziz.studentsspringbootdataclient.controllers.ControllerData;
import com.abdullaevaziz.studentsspringbootdataclient.model.Auto;
import com.abdullaevaziz.studentsspringbootdataclient.retrofit.AutoRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.prefs.Preferences;


public class UpdateAutoController implements ControllerData<Auto> {
    @FXML
    public Label idAutoLabel;
    @FXML
    public TextField idAutoTextField;
    @FXML
    public Label brandLabel;
    @FXML
    public TextField brandTextField;
    @FXML
    public Label powerLabel;
    @FXML
    public TextField powerTextField;
    @FXML
    public Label yearLabel;
    @FXML
    public TextField yearTextField;
    @FXML
    public Label idStudentLabel;
    @FXML
    public TextField idStudentTextField;
    private Auto auto;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    private AutoRepository autoRepository = new AutoRepository(login, password);


    @Override
    public void initData(Auto value) {
        this.auto = value;
        idAutoTextField.setText(String.valueOf(value.getId()));
        brandTextField.setText(value.getBrand());
        powerTextField.setText(String.valueOf(value.getPower()));
        yearTextField.setText(String.valueOf(value.getYear()));
        idStudentTextField.setText(String.valueOf(value.getId()));
    }

    @FXML
    public void addAuto(ActionEvent actionEvent) {
        String id = idAutoTextField.getText();
        String brand = brandTextField.getText();
        String power = powerTextField.getText();
        String year = yearTextField.getText();
        String studentId =idStudentTextField.getText();
        if(id.isEmpty() || brand.isEmpty() || power.isEmpty() || year.isEmpty() || studentId.isEmpty()){
            App.showAlert("Error!", "Не все поля заполнены!", Alert.AlertType.ERROR);
            return;
        }

        auto.setBrand(brandTextField.getText());
        auto.setPower(Integer.parseInt(powerTextField.getText()));
        auto.setYear(Integer.parseInt(yearTextField.getText()));

        try {
            if (auto != null) {
                this.autoRepository.put(auto);
                App.closeWindow(actionEvent);

            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (Exception e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
