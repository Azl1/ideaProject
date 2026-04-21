module com.kirillkotov.javafxlect {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens com.kirillkotov.javafxlect to javafx.fxml;
    exports com.kirillkotov.javafxlect;
    exports com.kirillkotov.javafxlect.controllers;
    opens com.kirillkotov.javafxlect.controllers to javafx.fxml;
    exports com.kirillkotov.javafxlect.model to com.fasterxml.jackson.databind;
}