package com.abdullaevaziz.cardfxspring.controllers.categorycontrollers;

import com.abdullaevaziz.cardfxspring.App;
import com.abdullaevaziz.cardfxspring.controllers.ControllerData;
import com.abdullaevaziz.cardfxspring.model.Category;
import com.abdullaevaziz.cardfxspring.repository.CategoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateCategoryController implements ControllerData<Category> {
    @FXML
    public TextField nameTextField;

    private CategoryRepository categoryRepository = new CategoryRepository();

    private Category category;

    @Override
    public void initData(Category value) {
        this.category = value;
        nameTextField.setText(category.getName());
    }

    @FXML
    public void updateCategory(ActionEvent actionEvent) throws IOException {
        category.setName(nameTextField.getText());
        try {
            if (category != null) {
                categoryRepository.update( category);
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IllegalArgumentException e) {
            App.showAlert("Error!", "Такая категория уже есть в базе данных!", Alert.AlertType.ERROR);
        }
    }

}
