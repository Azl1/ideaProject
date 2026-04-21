package com.abdullaevaziz.userfilesspringbootfx;

import lombok.*;
import com.abdullaevaziz.userfilesspringbootfx.controllers.ControllerData;
import com.abdullaevaziz.userfilesspringbootfx.util.Constants;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.prefs.Preferences;

public class App extends Application {
    private Preferences preferences = Preferences.userNodeForPackage(App.class);


    @Override
    public void start(Stage stage) {

        try {
            String tokenPref = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
            String res = "auth.fxml";

            if (tokenPref != null) {
                try {
                    res = "main.fxml";
                } catch (IllegalArgumentException e) {
                    preferences.remove(Constants.PREFERENCE_KEY_ID);
                    preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
                    e.printStackTrace();
                }
            }

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(res));
            AnchorPane root = fxmlLoader.load();
            Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
            stage.setTitle("Project user files!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //preferences.remove(Constants.PREFERENCE_KEY_ID);
        //preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
        //preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        launch();
    }

    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static <T> Stage getStage(String name, String title, T data) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(name));

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );

        stage.setTitle(title);

        if (data != null) {
            ControllerData<T> controller = loader.getController();
            controller.initData(data);
        }
        return stage;
    }

    public static <T> Stage openWindow(String name, String title, T data) throws IOException {
        Stage stage = getStage(name, title, data);
        stage.show();
        return stage;
    }

    public static <T> Stage openWindowAndWait(String name, String title, T data) throws IOException {
        Stage stage = getStage(name, title, data);
        stage.showAndWait();
        return stage;
    }

    public static void closeWindow(Event event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}