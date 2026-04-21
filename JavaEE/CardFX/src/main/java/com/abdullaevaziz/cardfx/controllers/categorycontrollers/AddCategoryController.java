package com.abdullaevaziz.cardfx.controllers.categorycontrollers;

import com.abdullaevaziz.cardfx.App;
import com.abdullaevaziz.cardfx.controllers.ControllerData;
import com.abdullaevaziz.cardfx.model.Category;
import com.abdullaevaziz.cardfx.model.User;
import com.abdullaevaziz.cardfx.repository.CategoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddCategoryController implements ControllerData<User> {
    @FXML
    public TextField nameTextField;

    private User user;
    private CategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public void initData(User value) {
        user = value;
    }

    @FXML
    public void addCategory(ActionEvent actionEvent) {
       long id = user.getId();
       String name = nameTextField.getText();
        Category newCategory = new Category(name);
        try {
            if (name.isEmpty()){
                App.showAlert("Error!", "Категория не введены данные!", Alert.AlertType.ERROR);
                return;
            }
            this.categoryRepository.add(user.getId(), newCategory);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }


}
