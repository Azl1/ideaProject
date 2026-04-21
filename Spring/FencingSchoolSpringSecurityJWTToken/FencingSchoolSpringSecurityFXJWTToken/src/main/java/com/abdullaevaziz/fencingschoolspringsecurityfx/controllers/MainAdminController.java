package com.abdullaevaziz.fencingschoolspringsecurityfx.controllers;

import com.abdullaevaziz.fencingschoolspringsecurityfx.App;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Admin;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Apprentice;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.Trainer;
import com.abdullaevaziz.fencingschoolspringsecurityfx.model.User;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.AdminRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.ApprenticeRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.TrainerRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.retrofit.UserRepository;
import com.abdullaevaziz.fencingschoolspringsecurityfx.util.Constants;
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

public class MainAdminController {

    @FXML
    public ListView<Admin> listAdmin;
    @FXML
    public Label mainLabel;
    @FXML
    public ListView<Trainer> listTrainers;
    @FXML
    public ListView<Apprentice> listApprentice;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private AdminRepository adminRepository = new AdminRepository(token);
    private TrainerRepository trainerRepository = new TrainerRepository(token);
    private ApprenticeRepository apprenticeRepository = new ApprenticeRepository(token);



    public void initialize() throws IOException {
        long id = this.preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
        String login = this.preferences.get(Constants.PREFERENCE_KEY_LOGIN, null);
        String password = preferences.get(Constants.PREFERENCE_KEY_PASSWORD, null);


        try {

            mainLabel.setText("Добро пожаловать, " + login);

            try {
                this.listAdmin.setItems(FXCollections.observableList(new AdminRepository(token).getAll()));
                this.listAdmin.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                            if (mouseEvent.getClickCount() == 2) {
                                Admin admin = listAdmin.getSelectionModel().getSelectedItem();
                                try {
                                    App.openWindow("updateAdmin.fxml", "Update admin info", admin);
                                    initialize();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                                }
                            }
                        }
                    }
                });

                this.listTrainers.setItems(FXCollections.observableList(new TrainerRepository(token).getAll()));
                this.listTrainers.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                            if (mouseEvent.getClickCount() == 2) {
                                Trainer trainer = listTrainers.getSelectionModel().getSelectedItem();
                                try {
                                    App.openWindow("mainTrainerInfo.fxml", "Update trainer info", trainer);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                    App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
                                }
                            }
                        }
                    }
                });

                this.listApprentice.setItems(FXCollections.observableList(new ApprenticeRepository(token).getAll()));
                this.listApprentice.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                            if (mouseEvent.getClickCount() == 2) {
                                Apprentice apprentice = listApprentice.getSelectionModel().getSelectedItem();
                                try {
                                    App.openWindow("mainApprenticeInfo.fxml", "Update apprentice info", apprentice);

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
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buttonExit(ActionEvent actionEvent) {
        preferences.remove(Constants.PREFERENCE_KEY_ID);
        preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
        preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
        App.closeWindow(actionEvent);
        try {
            App.openWindow("auth.fxml", "Authorization info", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonAddAdmin(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addTrainerApprenticeAdmin.fxml", "Admin info", null);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRemoveAdmin(ActionEvent actionEvent) {
        Admin selectedAdmin = this.listAdmin.getSelectionModel().getSelectedItem();
        if (selectedAdmin == null) {
            App.showAlert("Error!", "Выберите пользователя!", Alert.AlertType.ERROR);
            return;
        }
        try {
            long id = this.preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
            if (id == selectedAdmin.getId()) {
                this.preferences.put(Constants.PREFERENCE_KEY_TOKEN, token);
                this.preferences.put(Constants.PREFERENCE_KEY_LOGIN, selectedAdmin.getLogin());
                this.preferences.put(Constants.PREFERENCE_KEY_PASSWORD, selectedAdmin.getPassword());
                this.adminRepository.delete(selectedAdmin.getId());
                App.showAlert("Success!", "Администратор успешно удален!", Alert.AlertType.INFORMATION);
                App.openWindow("auth.fxml", "Authorization info", null);
                return;
            }
            this.adminRepository.delete(selectedAdmin.getId());
            this.listAdmin.getItems().remove(selectedAdmin);
            App.showAlert("Success!", "Администратор успешно удален!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            App.showAlert("Error!", "Ошибка при удалении администратора: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }

    }

    @FXML
    public void buttonUpdateAdmin(ActionEvent actionEvent) {
        Admin selectedItem = this.listAdmin.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem == null) {
                App.showAlert("Error!", "Выберите админа!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindow("updateAdmin.fxml", "Update Admin info", selectedItem);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonUpdateTrainer(ActionEvent actionEvent) {
        Trainer selectedItem = this.listTrainers.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem == null) {
                App.showAlert("Error!", "Выберите тренера!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("mainTrainerInfo.fxml", "Update Trainer info", selectedItem);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRemoveTrainer(ActionEvent actionEvent) {
        Trainer selectedTrainer = this.listTrainers.getSelectionModel().getSelectedItem();
        if (selectedTrainer == null) {
            App.showAlert("Error!", "Выберите пользователя!", Alert.AlertType.ERROR);
            return;
        }
        try {
            this.trainerRepository.delete(selectedTrainer.getId());
            this.listTrainers.getItems().remove(selectedTrainer);
            App.showAlert("Success!", "Тренер успешно удален!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            App.showAlert("Error!", "Ошибка при удалении тренера: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonUpdateApprentice(ActionEvent actionEvent) {
        Apprentice selectedItem = this.listApprentice.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem == null) {
                App.showAlert("Error!", "Выберите ученика!", Alert.AlertType.ERROR);
                return;
            }
            App.openWindow("mainApprenticeInfo.fxml", "Update Apprentice info", selectedItem);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonRemoveApprentice(ActionEvent actionEvent) {
        Apprentice selectedApprentice = this.listApprentice.getSelectionModel().getSelectedItem();
        if (selectedApprentice == null) {
            App.showAlert("Error!", "Выберите пользователя!", Alert.AlertType.ERROR);
            return;
        }
        try {
            this.apprenticeRepository.delete(selectedApprentice.getId());
            this.listApprentice.getItems().remove(selectedApprentice);
            App.showAlert("Success!", "Ученик успешно удален!", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            App.showAlert("Error!", "Ошибка при удалении ученика: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
