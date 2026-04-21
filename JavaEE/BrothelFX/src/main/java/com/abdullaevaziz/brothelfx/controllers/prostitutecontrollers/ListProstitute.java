package com.abdullaevaziz.brothelfx.controllers.prostitutecontrollers;

import com.abdullaevaziz.brothelfx.App;
import com.abdullaevaziz.brothelfx.controllers.ControllerData;
import com.abdullaevaziz.brothelfx.model.Client;
import com.abdullaevaziz.brothelfx.model.ProstituteIndividual;
import com.abdullaevaziz.brothelfx.repository.ProstituteRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ListProstitute implements ControllerData<Client> {

    @FXML
    public ListView<ProstituteIndividual> listPrstView;
    private Client client;
    private ProstituteRepository prostituteRepository = new ProstituteRepository();

    @FXML
    public void addButtonPrst(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addProstitute.fxml",
                    "Prostitute info", client);
            initData(client);
            App.showAlert("Info!", "Prostitute successfully add!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButtonPrst(ActionEvent actionEvent) {
        try {
            ProstituteIndividual selectedItem = this.listPrstView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select prostitute!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("updateProstitute.fxml", "Prostitute info", selectedItem);
            initData(client);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void removeButtonPrst(ActionEvent actionEvent) {
        try {
            ProstituteIndividual selectedItem = this.listPrstView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select prostituteIndividual!", Alert.AlertType.ERROR);
                return;
            }
            this.listPrstView.getItems().remove(selectedItem);
            this.prostituteRepository.delete(selectedItem.getId());
            App.showAlert("Info!", "Prostitute successfully deleted!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }


    @Override
    public void initData(Client valClient) {
        try {
            this.client = valClient;
            this.listPrstView.setItems(FXCollections.observableList(
                    new ProstituteRepository().getListProstitute(client.getId())));
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }


}
