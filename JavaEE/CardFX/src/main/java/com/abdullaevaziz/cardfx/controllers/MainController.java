package com.abdullaevaziz.cardfx.controllers;

import com.abdullaevaziz.cardfx.App;
import com.abdullaevaziz.cardfx.model.Category;
import com.abdullaevaziz.cardfx.model.User;
import com.abdullaevaziz.cardfx.repository.CategoryRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainController implements ControllerData<User>{

    @FXML
    public ListView<Category> listViewCategory;
    private CategoryRepository categoryRepository = new CategoryRepository();
    private User user;
    private Category category;

    @Override
    public void initData(User value) throws IOException {
        this.user = value;
        try {
            this.listViewCategory.setItems(FXCollections.observableList(new CategoryRepository().get(user.getId())));
            this.listViewCategory.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            Category category = listViewCategory.getSelectionModel().getSelectedItem();
                            try {

                                App.openWindow("listCard.fxml", "Card info", category);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    //Bivanov79248
    //55555
    //shura.kuznetsov
    //2131
    //mr.cuznetsoff
    //3211

    /**
     * 4. Main – главная форма приложения. Содержит в себе список всех категорий,
     * список карточек для выбранной категории.
     * При выборе карточки информация о ней отображается в полях для ввода,
     * где будет возможность произвести изменение данных выбранной карточки.
     * Так же есть возможность удаления как категории, так и карточки из списков системы.
     */

    @FXML
    public void addButtonCategory(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addCategory.fxml", "Add category info", this.user);
            this.initData(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButtonCategory(ActionEvent actionEvent) {
        try {
            Category selectedItem = this.listViewCategory.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select category", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("updateCategory.fxml", "Update category info", selectedItem);
            this.initData(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeButtonCategory(ActionEvent actionEvent) {
        try {
            Category selectedItem = this.listViewCategory.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select card", Alert.AlertType.ERROR);
                return;
            }
            try {
                this.categoryRepository.delete(selectedItem.getId());
               this.listViewCategory.getItems().remove(selectedItem);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            App.showAlert("Error!", " Category с таким user id несуществует!", Alert.AlertType.ERROR);
        }

        } catch (IOException e) {

            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void closeButton(ActionEvent actionEvent) {
        App.closeWindow(actionEvent);
    }
}
