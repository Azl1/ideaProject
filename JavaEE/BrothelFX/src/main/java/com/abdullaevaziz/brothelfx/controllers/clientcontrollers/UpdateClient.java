package com.abdullaevaziz.brothelfx.controllers.clientcontrollers;

import com.abdullaevaziz.brothelfx.App;
import com.abdullaevaziz.brothelfx.controllers.ControllerData;
import com.abdullaevaziz.brothelfx.model.Client;
import com.abdullaevaziz.brothelfx.repository.ClientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateClient implements ControllerData<Client> {

    @FXML
    public TextField fioTextField;
    @FXML
    public TextField numberTextField;
    @FXML
    public TextField ageTextField;
    @FXML
    public TextField preferencesTextField;
    private Client client;
    private ClientRepository clientRepository = new ClientRepository();

    @Override
    public void initData(Client valClient) {
        this.client = valClient;
        fioTextField.setText(valClient.getFio());
        numberTextField.setText(String.valueOf(valClient.getNumberTel()));
        ageTextField.setText(String.valueOf(valClient.getAge()));
        preferencesTextField.setText(valClient.getPreferences());
    }

    @FXML
    public void addClient(ActionEvent actionEvent) {
       client.setFio(String.valueOf(fioTextField.getText()));
       client.setNumberTel(Integer.parseInt(numberTextField.getText()));
       client.setAge(Integer.parseInt(ageTextField.getText()));
       client.setPreferences(String.valueOf(preferencesTextField.getText()));
        try {
            if (client != null){
                this.clientRepository.update(client);
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }


}
