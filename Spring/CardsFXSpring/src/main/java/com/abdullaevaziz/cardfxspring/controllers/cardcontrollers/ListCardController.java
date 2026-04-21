package com.abdullaevaziz.cardfxspring.controllers.cardcontrollers;

import com.abdullaevaziz.cardfxspring.App;
import com.abdullaevaziz.cardfxspring.controllers.ControllerData;
import com.abdullaevaziz.cardfxspring.model.Card;
import com.abdullaevaziz.cardfxspring.model.Category;
import com.abdullaevaziz.cardfxspring.repository.CardRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ListCardController implements ControllerData<Category> {
    private CardRepository cardRepository = new CardRepository();
    private Category category;
    @FXML
    public ListView<Card> listViewCard;

    @Override
    public void initData(Category value) throws IOException {
        this.category = value;
        try {
            this.listViewCard.setItems(FXCollections.observableList(new CardRepository().get(category.getId())));
            this.listViewCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Card card = listViewCard.getSelectionModel().getSelectedItem();
                            try {

                                App.openWindow("updateCard.fxml", "Update card info", card);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    @FXML
    public void addButtonCard(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addCard.fxml", "Add card info", this.category);
            this.initData(category);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButtonCard(ActionEvent actionEvent) {
        try {
            Card selectedItem = this.listViewCard.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select card", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("updateCard.fxml", "Update card info", selectedItem);
            this.initData(category);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeButtonCard(ActionEvent actionEvent) {
        try {
            Card selectedItem = this.listViewCard.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select card", Alert.AlertType.ERROR);
                return;
            }
            this.cardRepository.delete(selectedItem.getId());
            this.initData(category);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    public void closeButtonCard(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
    }

}
