package com.abdullaevaziz.tables.controllers;

import com.abdullaevaziz.modelData.User;
import com.abdullaevaziz.repository.UsersRepository;
import com.abdullaevaziz.tables.App;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class MainController {

    /**
     * 4. Произвести отображение всех пользователей в таблице по нажатию на опцию
     * File->Open. Выбрать созданный вручную файл с пользователями.
     * Программа должна отметить галочкой в первой колонке таблицы тех пользователей,
     * кому были уже отправлены письма. Саму галочку сделать неизменяемой,
     * написав следующий код в блоке инициализации после поля с CheckBox-ом:
     * {
     * btn.setDisable(true);
     * btn.setStyle("-fx-opacity: 1;");
     * }
     */

    /**
     * 5. Для каждой строки таблицы в крайней колонке должна быть кнопка «Отправить»,
     * по нажатию на которую происходит открытие нового окна с ожиданием закрытия
     * и передачей данных о пользователе, к которому она относится
     */

    @FXML
    public TableView<User> table;

    @FXML
    public void openMenuButton(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter1);
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter2);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            TableColumn<User, Integer> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<User, String> usernameCol = new TableColumn<>("Name");
            usernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<User, LocalDateTime> registrationDateCol = new TableColumn<>("Registration Date");
            registrationDateCol.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

            TableColumn<User, String> emailCol = new TableColumn<>("Email");
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<User, String> ageCol = new TableColumn<>("Age");
            ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));


            TableColumn<User, String> countryCol = new TableColumn<>("Country");
            countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

            TableColumn<User, Boolean> booleanSelectCol = new TableColumn<>("Select");
            //booleanSelectCol.setCellValueFactory(new PropertyValueFactory<>("isSend"));

            Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>> selectCol =
                    new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>() {
                        @Override
                        public TableCell call(final TableColumn<User, Boolean> param) {
                            final TableCell<User, Boolean> cell = new TableCell<User, Boolean>() {
                                CheckBox checkBox = new CheckBox();

                                {
                                    checkBox.setDisable(true);
                                    checkBox.setStyle("-fx-opacity: 1;");
                                }

                                @Override
                                public void updateItem(Boolean item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        checkBox.setSelected(table.getItems().get(getIndex()).isSend());
                                        setGraphic(checkBox);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };

            TableColumn<User, String> actionCol = new TableColumn<>("Action");

            Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory =
                    new Callback<TableColumn<User, String>, TableCell<User, String>>() {
                        @Override
                        public TableCell call(final TableColumn<User, String> param) {
                            final TableCell<User, String> cell = new TableCell<User, String>() {
                                final Button btn = new Button("Отправить");

                                {
                                    btn.getStyleClass().add("primary");
                                }

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {
                                            User user = getTableView().getItems().get(getIndex());
                                            try {
                                                App.openWindowAndWait("send.fxml", "Send info", user);

                                                table.setItems(FXCollections.observableList(
                                                        new UsersRepository(file).getUserArrayList()));
                                            } catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };

            actionCol.setCellFactory(cellFactory);

            booleanSelectCol.setCellFactory(selectCol);
            booleanSelectCol.setEditable(true);

            this.table.setItems(FXCollections.observableList(
                    new UsersRepository(file).getUserArrayList()));
            this.table.getColumns().setAll(idCol, usernameCol, registrationDateCol, emailCol, ageCol,
                    countryCol, booleanSelectCol, actionCol);
        }
    }




}
