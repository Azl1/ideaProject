package com.abdullaevaziz.userfilesversionsspringbootfx.controllers;

import com.abdullaevaziz.userfilesversionsspringbootfx.App;
import com.abdullaevaziz.userfilesversionsspringbootfx.model.User;
import com.abdullaevaziz.userfilesversionsspringbootfx.model.UserFile;
import com.abdullaevaziz.userfilesversionsspringbootfx.retrofit.UserFileRepository;
import com.abdullaevaziz.userfilesversionsspringbootfx.util.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class ListVersionController implements ControllerData<String>{

    @FXML
    public Label mainLabel;
    @FXML
    public ListView<UserFile> listFiles;
    private UserFileRepository userFileRepository;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private String loginPref = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);

    @Override
    public void initData(String value) {

        String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);

        userFileRepository = new UserFileRepository(token);

        mainLabel.setText("Добро пожаловать, " + loginPref);

        try {
            //TODO на сервер послать запрос с передачей туда токена и имени файла
            //TODO на сервере написать запрос который по имени файла и токену вернет тебе список всех юзерфайлов которые есть с таким именем у этого юзера
            List<UserFile> all = new UserFileRepository(token).getAllVersion(value);
            this.listFiles.setItems(FXCollections.observableList(all));
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void menuButtonSave(ActionEvent actionEvent) {
        try {
            UserFile selected = listFiles.getSelectionModel().getSelectedItem();
            if (selected == null) {
                App.showAlert("Error!", "Выберите файл из таблицы для сохранения", Alert.AlertType.ERROR);
                return;
            }

            String selectedUserFileGetName = listFiles.getSelectionModel().getSelectedItem().getFilename();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter);

            fileChooser.setInitialFileName(selectedUserFileGetName);
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                UserFile selectedUserFile = listFiles.getSelectionModel().getSelectedItem();
                if (selectedUserFile != null) {
                    new UserFileRepository(token).downloadFileAndVersion(file, selectedUserFile.getVersion());
                    App.showAlert("Info!", "Файл успешно сохранен: ", Alert.AlertType.INFORMATION);
                } else {
                    App.showAlert("Error!", "Выберите файл из таблицы для сохранения ", Alert.AlertType.ERROR);
                }
            }
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void buttonExit(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
    }

}
