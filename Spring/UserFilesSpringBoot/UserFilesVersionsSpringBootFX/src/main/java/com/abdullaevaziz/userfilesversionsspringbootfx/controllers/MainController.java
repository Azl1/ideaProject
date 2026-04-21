package com.abdullaevaziz.userfilesversionsspringbootfx.controllers;

import com.abdullaevaziz.userfilesversionsspringbootfx.App;
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
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class MainController {


    @FXML
    public Label mainLabel;
    @FXML
    public ListView<String> listFiles;
    private UserFileRepository userFileRepository;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private String loginPref = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);

    @FXML
    public void initialize() throws IOException {

        String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);

        userFileRepository = new UserFileRepository(token);

        mainLabel.setText("Добро пожаловать, " + loginPref);

        try {
            List<UserFile> all = new UserFileRepository(token).getAll();
            List<String> uniqueFileNames = all.stream().map(UserFile::getFilename).distinct().collect(Collectors.toList());

            this.listFiles.setItems(FXCollections.observableArrayList(uniqueFileNames));
            this.listFiles.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            String selectedFileName = listFiles.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindow("listVersion.fxml", "List version info", selectedFileName);
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
    public void menuButtonAdd(ActionEvent actionEvent) {
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
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);

        }
    }

    @FXML
    public void menuButtonDownloadZip(ActionEvent actionEvent) {
        try {
            String selectedUserFileGetName = listFiles.getSelectionModel().getSelectedItem();
            if (selectedUserFileGetName == null) {
                App.showAlert("Error!", "Выберите файл из таблицы для сохранения", Alert.AlertType.ERROR);
                return;
            }
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter);

            String zipFileName = selectedUserFileGetName;
            if (!zipFileName.toLowerCase().endsWith(".zip")) {
                zipFileName += ".zip";
            }
            fileChooser.setInitialFileName(zipFileName);
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                String selectedUserFile = listFiles.getSelectionModel().getSelectedItem();
                if (selectedUserFile != null) {

                    new UserFileRepository(token).downloadZip(selectedUserFileGetName, file);
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

    public void menuButtonDownloadAllZip(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter);

            fileChooser.setInitialFileName("user_files.zip");
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                String selectedUserFile = listFiles.getSelectionModel().getSelectedItem();

                new UserFileRepository(token).downloadZipList(file);
                initialize();
                App.showAlert("Info!", "Файл успешно сохранен: ", Alert.AlertType.INFORMATION);

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
