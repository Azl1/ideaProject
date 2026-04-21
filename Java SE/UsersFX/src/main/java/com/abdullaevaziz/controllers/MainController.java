package com.abdullaevaziz.controllers;


import com.abdullaevaziz.combobox.App;
import com.abdullaevaziz.model.Address;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.repository.RepositoryUser;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class MainController {

    @FXML
    public Label UserList;
    @FXML
    public ComboBox<User> userBox;

    @FXML
    public ListView<User> listView;

    private RepositoryUser repositoryUser = new RepositoryUser();

    //TODO сюда записать перемннею файл типа данных Файл
    File fileMain;

    @FXML
    public void initialize() throws IOException {
        this.userBox.setItems(FXCollections.observableList(new RepositoryUser("https://jsonplaceholder.typicode.com/users").getUsers()));
        this.listView.setItems(FXCollections.observableList(
                new RepositoryUser().getUsers()));
    }


    public void save(ActionEvent actionEvent) {
        try {
            User selectedItem = this.userBox.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select user", Alert.AlertType.ERROR);
                return;
            }
            new RepositoryUser().add(selectedItem);
            this.listView.getItems().add(selectedItem);
            this.userBox.getItems().remove(selectedItem);
            this.repositoryUser.add(selectedItem);

            App.showAlert("Info!", "User successfully added!", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            App.showAlert("Error!", "Incorrect format number", Alert.AlertType.ERROR);
        }
    }

    public void delete(ActionEvent actionEvent) {
        User selectedItem = this.listView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            App.showAlert("Error!", "Select user", Alert.AlertType.ERROR);
            return;
        }
        new RepositoryUser().delete(selectedItem);
        this.listView.getItems().remove(selectedItem);
        this.repositoryUser.delete(selectedItem);
        App.showAlert("Info!", "User successfully deleted!", Alert.AlertType.INFORMATION);
    }

    public void menuButtonOpen(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter1);
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter2);
        FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter3);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            this.userBox.setItems(FXCollections.observableList(new RepositoryUser(file).getUsers()));
        }
    }


    public void menuButtonSave(ActionEvent actionEvent) throws IOException {
        if(this.fileMain == null) {
            menuButtonSaveAs(actionEvent);
        } else {
            this.repositoryUser.save(this.fileMain);
        }
    }

    public void menuButtonSaveAs(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter1);
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter2);
        FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("Word files (*.docx)", "*.docx");
        fileChooser.getExtensionFilters().add(extFilter3);

        this.fileMain = fileChooser.showSaveDialog(null);
        if(this.fileMain != null){
            this.repositoryUser.save(this.fileMain);
        }
    }

    /**
     * 1. В проекте UsersFX по выбору элемента из ListView и нажатию кнопки “Инфо”
     * произвести открытие нового окна UserInfoController, куда будет передан выбранный элемент
     */
    public void info(ActionEvent actionEvent) {
        try {
            User user1 =  listView.getSelectionModel().getSelectedItem();
            //App.openWindow("second.fxml", "User info", user1 );
            App.openWindowAndWait("second.fxml", "User info", user1);
            App.showAlert("Info!", "Просмотр объекта закончен!", Alert.AlertType.INFORMATION);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
