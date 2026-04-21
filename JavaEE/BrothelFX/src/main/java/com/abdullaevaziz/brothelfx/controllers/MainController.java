package com.abdullaevaziz.brothelfx.controllers;

import com.abdullaevaziz.brothelfx.App;
import com.abdullaevaziz.brothelfx.model.Client;
import com.abdullaevaziz.brothelfx.repository.ClientRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainController {
    @FXML
    public ListView<Client> listViewClients;
    @FXML
    public Label labelClients;

    ClientRepository clientRepository = new ClientRepository();

    public void initialize() {
        try {
            this.listViewClients.setItems(FXCollections.observableList(
                    new ClientRepository().get()));
            this.listViewClients.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Client client = listViewClients.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindow("listProstitute.fxml", "Prostitute info", client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }


    @FXML
    public void addButtonCl(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addClient.fxml", "Client info", null);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButtonCl(ActionEvent actionEvent) {
        try {
            Client selectedItem = this.listViewClients.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select client!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("updateClient.fxml", "Client info", selectedItem);
            initialize();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void removeButtonCl(ActionEvent actionEvent) {
        try {
            Client selectedItem = this.listViewClients.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select client!", Alert.AlertType.ERROR);
                return;
            }
            this.clientRepository.delete(selectedItem.getId());
            initialize();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonShowProstitute(ActionEvent actionEvent) {
        try {
            Client selectedItem = this.listViewClients.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select client!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("listProstitute.fxml", "Prostitute info", selectedItem);
            initialize();
        } catch (IOException e) {
            App.showAlert("Info!", "Select a client!", Alert.AlertType.INFORMATION);
        }
    }
}
