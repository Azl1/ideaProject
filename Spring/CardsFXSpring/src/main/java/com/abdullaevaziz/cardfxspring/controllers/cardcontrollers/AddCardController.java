package com.abdullaevaziz.cardfxspring.controllers.cardcontrollers;

import com.abdullaevaziz.cardfxspring.App;
import com.abdullaevaziz.cardfxspring.controllers.ControllerData;
import com.abdullaevaziz.cardfxspring.model.Card;
import com.abdullaevaziz.cardfxspring.model.Category;
import com.abdullaevaziz.cardfxspring.repository.CardRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddCardController implements ControllerData<Category> {
    @FXML
    public TextField questionTextField;
    @FXML
    public TextField answerTextField;

    private CardRepository cardRepository = new CardRepository();
    private Category category;

    @Override
    public void initData(Category value) {
        this.category = value;
    }

    @FXML
    public void addCard(ActionEvent actionEvent) {
        String question = questionTextField.getText();
        String answer = answerTextField.getText();
        Card newCard = new Card(question, answer);
        try {
            if (question.isEmpty() || answer.isEmpty()) {
                App.showAlert("Error!", "Карточка не введены данные!", Alert.AlertType.ERROR);
                return;
            }
            this.cardRepository.add(category.getId(), newCard);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
