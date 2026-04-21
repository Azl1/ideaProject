package com.abdullaevaziz.cardfx.controllers.cardcontrollers;

import com.abdullaevaziz.cardfx.App;
import com.abdullaevaziz.cardfx.controllers.ControllerData;
import com.abdullaevaziz.cardfx.model.Card;
import com.abdullaevaziz.cardfx.repository.CardRepository;
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
    public void updateCard(ActionEvent actionEvent) {
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
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

    }

}
