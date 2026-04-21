package com.abdullaevaziz.userfilesservletsfx.controllers;

import com.abdullaevaziz.userfilesservletsfx.App;
import com.abdullaevaziz.userfilesservletsfx.client.HttpMultipart;
import com.abdullaevaziz.userfilesservletsfx.constants.Constants;
import com.abdullaevaziz.userfilesservletsfx.model.User;
import com.abdullaevaziz.userfilesservletsfx.model.UserFile;
import com.abdullaevaziz.userfilesservletsfx.repository.UserFileRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class ListUserFiles implements ControllerData<User>{


    @FXML
    private ListView<UserFile> listViewUserFile;
    private User user;
    private UserFile userFile;
    @Override
    public void initData(User value) {
        this.user = value;
        try {
            this.listViewUserFile.setItems(FXCollections.observableList(new UserFileRepository().get(user.getId())));
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void sendFileButton(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);

            Map<String, String> headers = new HashMap<>();
            HttpMultipart multipart = new HttpMultipart(
                    Constants.SERVER_URL + "/file_upload_servlet", "utf-8", headers);
            //multipart.addFormField("filename", "test_name");
            try {
                if (file != null) {
                    System.out.println(file);
                    multipart.addFilePart("filename", file);
                    multipart.addFormField("user_id", String.valueOf(user.getId()));
                }
                String response = multipart.finish();
                System.out.println(response);
                initData(user);
            } catch (IOException e){
                App.showAlert("Info", "Такой file для этого usera уже существует в системе!", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void saveFileButton(ActionEvent actionEvent) {
        try {
              this.userFile = listViewUserFile.getSelectionModel().getSelectedItem();
              if(this.userFile == null){
                  App.showAlert("Error", "Файл файл не выбран!", Alert.AlertType.ERROR);
                  return;
              }
            String s = Constants.SERVER_URL + "/file_upload_servlet?user_id=" + user.getId()
                    + "&filename=" +  URLEncoder.encode(userFile.getFilename(), StandardCharsets.UTF_8);

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
            fileChooser.getExtensionFilters().add(extFilter1);

            fileChooser.setInitialFileName(userFile.getFilename());

            File downloaded = fileChooser.showSaveDialog(null);
            if(downloaded != null) {
                HttpMultipart.getMultiPart(s, downloaded.getAbsolutePath());
                App.showAlert("Info", "Файл успешно сохранен!", Alert.AlertType.INFORMATION);
            }

        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Info", "FileName не найден!", Alert.AlertType.ERROR);
        }
    }



}
