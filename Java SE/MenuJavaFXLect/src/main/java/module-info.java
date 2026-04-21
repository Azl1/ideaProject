module com.kirillkotov.menujavafxlect {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kirillkotov.menujavafxlect to javafx.fxml;
    exports com.kirillkotov.menujavafxlect;
    exports com.kirillkotov.menujavafxlect.controllers;
    opens com.kirillkotov.menujavafxlect.controllers to javafx.fxml;
}