package com.abdullaevaziz.fileuploaderspringbootfx.controllers;

import com.abdullaevaziz.fileuploaderspringbootfx.App;
import com.abdullaevaziz.fileuploaderspringbootfx.retrofit.UserFileRepository;
import com.abdullaevaziz.fileuploaderspringbootfx.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.prefs.Preferences;

public class AddPathClientController implements ControllerData<String> {

    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private UserFileRepository userFileRepository = new UserFileRepository(token);
    @FXML
    private TextField nameTextField;
    private String currentPath;

    @Override
    public void initData(String value)  {
        currentPath = value;
    }

    @FXML
    public void buttonAddPath(ActionEvent actionEvent) {

        String nameDir = nameTextField.getText();

        if (nameDir == null || nameDir.isBlank()) return;

        new File(currentPath, nameDir).mkdirs();

                App.showAlert("INFO", "Папка добавлена", Alert.AlertType.INFORMATION);
                App.closeWindow(actionEvent);


    }


}
