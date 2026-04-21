package com.abdullaevaziz.fencingschoolfx.controllers;

import com.abdullaevaziz.fencingschoolfx.App;
import com.abdullaevaziz.fencingschoolfx.constants.Constants;
import com.abdullaevaziz.fencingschoolfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolfx.model.Trainer;
import com.abdullaevaziz.fencingschoolfx.model.User;
import com.abdullaevaziz.fencingschoolfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolfx.retrofit.UserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.prefs.Preferences;

public class MainController implements ControllerData<User> {

    @FXML
    public ListView<Apprentice> listApprentice;
    @FXML
    public ListView<Trainer> listTrainer;
    @FXML
    public Label mainLabel;
    private User user;
    private UserRepository userRepository = new UserRepository();
    private ApprenticeRepository apprenticeRepository = new ApprenticeRepository();
    private TrainerRepository trainerRepository = new TrainerRepository();
    private Preferences preferences = Preferences.userNodeForPackage(App.class);

    public void initialize() throws IOException {
        long idUser = this.preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);

        String login = this.preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
        mainLabel.setText("Добро пожаловать, " + login);

        user = this.userRepository.getUserId(idUser);

        try {
            this.listApprentice.setItems(FXCollections.observableList(new ApprenticeRepository().getAll()));
            this.listApprentice.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Apprentice apprentice = listApprentice.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindowAndWait("apprentice.fxml", "Apprentice info", apprentice);
                                initialize();
                            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }

        try {
            this.listTrainer.setItems(FXCollections.observableList(new TrainerRepository().getAll()));
            this.listTrainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Trainer trainer = listTrainer.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindowAndWait("trainer.fxml", "Trainer info", trainer);
                                initialize();
                            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    public void updateListButton(ActionEvent actionEvent) throws IOException {
        initialize();
    }

    @Override
    public void initData(User value) throws IOException {

    }

    @FXML
    public void buttonRemoveUser(ActionEvent actionEvent) {
        try {
            Apprentice selectedApprentice = this.listApprentice.getSelectionModel().getSelectedItem();
            Trainer selectedTrainer = this.listTrainer.getSelectionModel().getSelectedItem();
            if (selectedApprentice != null) {
                try {
                    this.apprenticeRepository.delete(selectedApprentice.getId());
                    this.listApprentice.getItems().remove(selectedApprentice);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    App.showAlert("Error!", "Apprentice с таким id не существует!", Alert.AlertType.ERROR);
                }
            } else if (selectedTrainer != null) {
                try {
                    this.trainerRepository.delete(selectedTrainer.getId());
                    this.listTrainer.getItems().remove(selectedTrainer);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    App.showAlert("Error!", "Trainer с таким id не существует!", Alert.AlertType.ERROR);
                }
            } else {
                App.showAlert("Error!", "Выберите ученика или тренера!", Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Ошибка соединения с сервером!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonExit(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonAddUser(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addTraining.fxml", "Training info", null);
            initialize();
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
