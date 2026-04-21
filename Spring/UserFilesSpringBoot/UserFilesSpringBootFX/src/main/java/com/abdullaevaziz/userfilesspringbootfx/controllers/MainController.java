package com.abdullaevaziz.userfilesspringbootfx.controllers;

import com.abdullaevaziz.userfilesspringbootfx.App;
import com.abdullaevaziz.userfilesspringbootfx.model.UserFile;
import com.abdullaevaziz.userfilesspringbootfx.retrofit.UserFileRepository;
import com.abdullaevaziz.userfilesspringbootfx.util.Constants;
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
import java.util.prefs.Preferences;

public class MainController {


    @FXML
    public Label mainLabel;
    @FXML
    public ListView<UserFile> listFiles;
    private UserFileRepository userFileRepository;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private String loginPref = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);

    public void initialize() throws IOException {

        String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);

        userFileRepository = new UserFileRepository(token);

        mainLabel.setText("Добро пожаловать, " + loginPref);

        try {
            this.listFiles.setItems(FXCollections.observableList(new UserFileRepository(token).getAll()));
            this.listFiles.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            UserFile userFile = listFiles.getSelectionModel().getSelectedItem();
                            try {
                                //App.openWindow("updateAdmin.fxml", "Update admin info", userFile);
                                initialize();
                            } catch (IOException e) {
                                e.printStackTrace();
                                App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                            }
                        }
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void menuButtonOpen(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter3);
            //TODO set initial directory
            //fileChooser.setInitialDirectory(new File(....));
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                UserFile userFileNew = this.userFileRepository.uploadFile(file);
                initialize();
            }
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);

        }
    }

    @FXML
    public void menuButtonSave(ActionEvent actionEvent) {
        try {
            UserFile selectedUserFileGetName = listFiles.getSelectionModel().getSelectedItem();
            if (selectedUserFileGetName == null) {
                App.showAlert("Error!", "Выберите файл из таблицы для сохранения", Alert.AlertType.ERROR);
                return;
            }
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter);

            fileChooser.setInitialFileName(selectedUserFileGetName.getFilename());
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                UserFile selectedUserFile = listFiles.getSelectionModel().getSelectedItem();
                if (selectedUserFile != null) {
                    new UserFileRepository(token).downloadFile(file);
                    initialize();
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
        preferences.remove(Constants.PREFERENCE_KEY_ID);
        preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
        preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
