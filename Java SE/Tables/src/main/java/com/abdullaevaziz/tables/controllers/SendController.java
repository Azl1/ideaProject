package com.abdullaevaziz.tables.controllers;

import com.abdullaevaziz.exception.InsufficientFundsException;
import com.abdullaevaziz.modelData.User;
import com.abdullaevaziz.repository.SendUserIdRepository;
import com.abdullaevaziz.repository.UsersRepository;
import com.abdullaevaziz.tables.App;
import com.abdullaevaziz.util.Constants;
import com.abdullaevaziz.util.MailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;

public class SendController implements ControllerData<User> {

    /**
     * 6. В новом окне сделать поля для ввода темы письма(textField) и текста письма(textArea).
     * По нажатию на кнопку «Отправить» в этом окне произвести отправку письма пользователю и вывода сообщения об успехе,
     * сохранив id этого пользователя в файл send.json через репозиторий.
     * Для отправки сообщения использовать код из приложения к проекту
     */

    @FXML
    TextField subject;
    @FXML
    TextArea area;
    private User user;

    @Override
    public void initData(User value) {
        this.user = value;

    }

    public void buttonSend(ActionEvent actionEvent)  {
        String subject = this.subject.getText();
        String area = this.area.getText();
        String accountFrom = "twe12345@bk.ru";
        String password = "6DMvP77QH4WziMA1rvuN";
        // String accountTo = "abdullaev12@bk.ru";
        String accountTo = this.user.getEmail();
        try {
        MailSender mailSender = new MailSender(accountFrom, password, accountTo);
            mailSender.send(subject, area);
            SendUserIdRepository sendUserIdRepository = new SendUserIdRepository();
            sendUserIdRepository.add(user.getId());
            App.showAlert("Info!", "Успешно отправлено пользователю!", Alert.AlertType.INFORMATION);
        } catch (IllegalArgumentException ie) {
            App.showAlert("Info!", ie.getMessage(), Alert.AlertType.ERROR);
        }
    }


    /**
     * 7. После закрытия окна с отправлением сообщения необходимо
     * произвести инициализацию таблицы заново для того,
     * чтобы галочка об отправке отобразилась в первой колонке автоматически
     */
    public void buttonClose(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
    }

    @Override
    public String toString() {
        return "SendController{" +
                " user=" + user.getId() +
                '}';
    }
}
