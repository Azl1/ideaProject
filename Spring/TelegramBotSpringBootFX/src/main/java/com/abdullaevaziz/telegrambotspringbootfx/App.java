package com.abdullaevaziz.telegrambotspringbootfx;

import com.abdullaevaziz.telegrambotspringbootfx.constants.Constants;
import com.abdullaevaziz.telegrambotspringbootfx.controllers.ControllerData;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.prefs.Preferences;

public class App extends Application {

    private Preferences preferences = Preferences.userNodeForPackage(App.class);

    @Override
    public void start(Stage stage) throws IOException {
        String res;
        String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
        String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);
        if (login != null && password != null) {
            res = "main.fxml";
        } else {
            res = "auth.fxml";
        }

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(res));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Main window!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
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

    public static void closeWindow(Event event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}