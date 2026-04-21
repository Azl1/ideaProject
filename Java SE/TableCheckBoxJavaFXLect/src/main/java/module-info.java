module com.kirillkotov.tablecheckboxjavafxlect {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kirillkotov.tablecheckboxjavafxlect to javafx.fxml;
    exports com.kirillkotov.tablecheckboxjavafxlect;
    exports com.kirillkotov.tablecheckboxjavafxlect.controllers;
    opens com.kirillkotov.tablecheckboxjavafxlect.controllers to javafx.fxml;
    opens com.kirillkotov.tablecheckboxjavafxlect.model to javafx.base;
}