package com.abdullaevaziz.cardfxspring.controllers.cardcontrollers;

import com.abdullaevaziz.cardfxspring.App;
import com.abdullaevaziz.cardfxspring.controllers.ControllerData;
import com.abdullaevaziz.cardfxspring.model.Card;
import com.abdullaevaziz.cardfxspring.repository.CardRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateCardController implements ControllerData<Card> {
    @FXML
    public TextField questionTextField;
    @FXML
    public TextField answerTextField;
    @FXML
    public TextField categoryIdTextField;
    private Card card;
    private CardRepository cardRepository = new CardRepository();

    @Override
    public void initData(Card value) {
        this.card = value;
        questionTextField.setText(value.getQuestion());
        answerTextField.setText(value.getAnswer());
    }

    @FXML
    public void updateCard(ActionEvent actionEvent) throws IOException {
        card.setQuestion(questionTextField.getText());
        card.setAnswer(answerTextField.getText());
        try {
            if (card != null) {
                try {
                    cardRepository.update(card);
                    App.closeWindow(actionEvent);
                } catch (IllegalArgumentException e) {
                    App.showAlert("Error!", "Такая карточка уже есть в базе данных!", Alert.AlertType.ERROR);
                }
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

    }

}
