package com.abdullaevaziz.brothelfx.controllers.clientcontrollers;

import com.abdullaevaziz.brothelfx.App;
import com.abdullaevaziz.brothelfx.model.Client;
import com.abdullaevaziz.brothelfx.repository.ClientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddClient {

    @FXML
    public TextField fioTextField;
    @FXML
    public TextField numberTextField;
    @FXML
    public TextField ageTextField;
    @FXML
    public TextField preferencesTextField;
    private ClientRepository clientRepository = new ClientRepository();


    @FXML
    public void addClient(ActionEvent actionEvent) {
        Client client = new Client(fioTextField.getText(),
                Integer.parseInt(numberTextField.getText()),
                Integer.parseInt(ageTextField.getText()),
                preferencesTextField.getText());
        try {
            this.clientRepository.add(client);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
