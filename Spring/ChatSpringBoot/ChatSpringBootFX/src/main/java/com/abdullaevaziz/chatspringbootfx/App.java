package com.abdullaevaziz.chatspringbootfx;



import com.abdullaevaziz.chatspringbootfx.controllers.ControllerData;
import com.abdullaevaziz.chatspringbootfx.model.User;
import com.abdullaevaziz.chatspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.chatspringbootfx.util.Constants;
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

    private static Preferences preferences = Preferences.userNodeForPackage(App.class);
    private Long id = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
    private String login = preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
    private String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);


    @Override
    public void start(Stage stage) throws IOException {

        String res = "auth.fxml";

        if (login != null && password != null && id != -1) {
            try {

                UserRepository userRepository = new UserRepository();
                User userReg = userRepository.getByLoginAndPassword(login, password);

            } catch (IllegalArgumentException e) {
                preferences.remove(Constants.PREFERENCE_KEY_ID);
                preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
                preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
                e.printStackTrace();
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
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