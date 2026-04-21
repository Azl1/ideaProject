package com.abdullaevaziz.brothelfx.controllers.prostitutecontrollers;

import com.abdullaevaziz.brothelfx.App;
import com.abdullaevaziz.brothelfx.controllers.ControllerData;
import com.abdullaevaziz.brothelfx.model.Client;
import com.abdullaevaziz.brothelfx.model.ProstituteIndividual;
import com.abdullaevaziz.brothelfx.repository.ProstituteRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddProstitute implements ControllerData<Client> {

    @FXML
    public TextField fioPrstTextField;
    @FXML
    public TextField agePrstTextField;
    @FXML
    public TextField weightPrstTextField;
    @FXML
    public TextField specializationPrstTextField;
    @FXML
    public TextField pricePrstTextField;
    @FXML
    public TextField idClientTextField;
    private ProstituteRepository prostituteRepository = new ProstituteRepository();
    private Client client;

    @Override
    public void initData(Client valueClient) {
        this.client = valueClient;
        idClientTextField.setText(String.valueOf(client.getId()));

    }

    @FXML
    public void addProstitute(ActionEvent actionEvent) throws IOException {

        ProstituteIndividual prostituteIndividualNew = new ProstituteIndividual(fioPrstTextField.getText(),
                Integer.parseInt(agePrstTextField.getText()), Integer.parseInt(weightPrstTextField.getText()),
                specializationPrstTextField.getText(), Double.parseDouble(pricePrstTextField.getText()),
                client.getId());
        try {
            this.prostituteRepository.add(prostituteIndividualNew);
            App.closeWindow(actionEvent);
        } catch (NullPointerException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }


}
