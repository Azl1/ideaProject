package com.abdullaevaziz.cardfx.controllers.usercontroller;

import com.abdullaevaziz.cardfx.model.User;
import com.abdullaevaziz.cardfx.repository.CategoryRepository;
import com.abdullaevaziz.cardfx.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ListUserController {

    @FXML
    private ListView<User> listViewUser;
    private UserRepository userRepository = new UserRepository();
    private CategoryRepository categoryRepository = new CategoryRepository();

    @FXML
    public void initialize(){

    }

   /* @FXML
    private ListView<User> listViewUser;
    private UserRepository userRepository = new UserRepository();


    @FXML
    public void initialize(){
        try {
            this.listViewUser.setItems(FXCollections.observableList(new UserRepository().get()));
            this.listViewUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 2) {
                            User user = listViewUser.getSelectionModel().getSelectedItem();
                            try {
                                App.openWindow("main.fxml", "Category info", user);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void addButtonU(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addUser.fxml", "Add user info!", null);
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void updateButtonU(ActionEvent actionEvent) {
        try {
            User selectedItem = this.listViewUser.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select user", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("updateUser.fxml", "Update user info !", null);
            initialize();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void removeButtonU(ActionEvent actionEvent) {
        try {
            User selectedItem = this.listViewUser.getSelectionModel().getSelectedItem();
            if (selectedItem == null){
                App.showAlert("Error!", "Select user", Alert.AlertType.ERROR);
                return;
            }
            this.userRepository.delete(selectedItem.getId());
            initialize();
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void buttonShowCategory(ActionEvent actionEvent) {
        try {
            User selectedItem = this.listViewUser.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                App.showAlert("Error!", "Select user", Alert.AlertType.ERROR);
                return;
            }
            App.openWindowAndWait("main.fxml", "Category info", selectedItem);
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }*/
}
