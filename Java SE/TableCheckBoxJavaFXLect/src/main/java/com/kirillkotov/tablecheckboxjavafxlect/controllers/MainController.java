package com.kirillkotov.tablecheckboxjavafxlect.controllers;

import com.kirillkotov.tablecheckboxjavafxlect.model.Person;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

public class MainController {
    @FXML
    public TableView<Person> tableView;

    @FXML
    void initialize(){
        TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("First Name");
        TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>("Last Name");
        TableColumn<Person, Boolean> vegetarianCol = new TableColumn<Person, Boolean>("Vegetarian");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            people.add(new Person("P" + i, "qwe", i % 5 == 0));
        }

        tableView.setItems(FXCollections.observableArrayList(people));

        tableView.getColumns().setAll(firstNameCol, lastNameCol, vegetarianCol);
        Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>> cellFactory =
                new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
                    @Override
                    public TableCell call(final TableColumn<Person, Boolean> param) {
                        final TableCell<Person, Boolean> cell = new TableCell<Person, Boolean>() {
                            CheckBox btn = new CheckBox("ds");

                            @Override
                            public void updateItem(Boolean item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setSelected(tableView.getItems().get(getIndex()).isVegetarian());
                                    btn.setOnAction(event -> {
                                        Person person = getTableView().getItems().get(getIndex());
                                        person.setVegetarian(!person.isVegetarian());
                                        System.out.println(person + " " + person.isVegetarian());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        vegetarianCol.setCellFactory(cellFactory);
        vegetarianCol.setEditable(true);
    }

}
