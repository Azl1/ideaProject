package com.kirillkotov.tablebuttonsjavafxlect.controllers;

import com.kirillkotov.tablebuttonsjavafxlect.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class MainController {
    @FXML
    public TableView<Person> table;

    public void initialize(){
        ObservableList<Person> data
                = FXCollections.observableArrayList(
                new Person("Jacob", "Smith"),
                new Person("Isabella", "Johnson"),
                new Person("Ethan", "Williams"),
                new Person("Emma", "Jones"),
                new Person("Michael", "Brown")
        );

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Person, String> actionCol = new TableColumn<>("Action");

        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory =
                new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Person, String> param) {
                        final TableCell<Person, String> cell = new TableCell<Person, String>() {
                            final Button btn = new Button("Just Do It");

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
                                        Person person = getTableView().getItems().get(getIndex());
                                        System.out.println(person.getFirstName()
                                                + "   " + person.getLastName());
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
        table.setItems(data);
        table.getColumns().setAll(firstNameCol, lastNameCol, actionCol);
    }

}
