package com.abdullaevaziz.cardfx.controllers.categorycontrollers;

import com.abdullaevaziz.cardfx.App;
import com.abdullaevaziz.cardfx.controllers.ControllerData;
import com.abdullaevaziz.cardfx.model.Category;
import com.abdullaevaziz.cardfx.repository.CategoryRepository;
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
    public void initData(Category value) throws IOException {
        this.category = value;
        nameTextField.setText(category.getName());
    }

    @FXML
    public void updateCategory(ActionEvent actionEvent) {
        category.setName(nameTextField.getText());
        try {
            if (category != null) {
                categoryRepository.update(category.getUser().getId(), category);
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Такая категория уже есть в базе данных!", Alert.AlertType.ERROR);
        }
    }

}
