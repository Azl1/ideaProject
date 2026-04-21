package com.abdullaevaziz.telegrambotspringbootfx.controllers;

import com.abdullaevaziz.telegrambotspringbootfx.App;
import com.abdullaevaziz.telegrambotspringbootfx.constants.Constants;
import com.abdullaevaziz.telegrambotspringbootfx.model.TelegramUser;
import com.abdullaevaziz.telegrambotspringbootfx.retrofit.TelegramUserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class TelegramUserController implements ControllerData<TelegramUser> {
    @FXML
    public TextArea textArea;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
    private TelegramUserRepository telegramUserRepository = new TelegramUserRepository(login, password);
    private TelegramUser telegramUser;

    @Override
    public void initData(TelegramUser value) {
        this.telegramUser = value;
    }

    @FXML
    public void sendButton(ActionEvent actionEvent) {
        String message = this.textArea.getText().trim();
        if (message.isEmpty()){
            App.showAlert("Error!", "Введите сообщение!", Alert.AlertType.ERROR);
            return;
        }
        try {
            this.telegramUserRepository.sendMessage(telegramUser.getId(), message);
            App.showAlert("Info!", "Сообщение отправлено!", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            App.showAlert("Error!", "Не верный формат!", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void imageButton(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter3);
            //fileChooser.setInitialDirectory(new File(....));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                this.telegramUserRepository.sendImage(telegramUser.getId(), file);
                App.showAlert("Info!", "Картина отправлена!", Alert.AlertType.INFORMATION);
            }
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void audioButton(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter3);
            //fileChooser.setInitialDirectory(new File(....));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                this.telegramUserRepository.sendAudio(telegramUser.getId(), file);
                App.showAlert("Info!", "Аудио отправлена!", Alert.AlertType.INFORMATION);
            }
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void documentButton(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter3);
            //fileChooser.setInitialDirectory(new File(....));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                this.telegramUserRepository.sendDocument(telegramUser.getId(), file);
                App.showAlert("Info!", "Документ отправлен!", Alert.AlertType.INFORMATION);
            }
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
