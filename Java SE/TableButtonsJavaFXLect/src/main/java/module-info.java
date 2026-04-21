module com.kirillkotov.tablebuttonsjavafxlect {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kirillkotov.tablebuttonsjavafxlect to javafx.fxml;
    exports com.kirillkotov.tablebuttonsjavafxlect;
    exports com.kirillkotov.tablebuttonsjavafxlect.controllers;
    opens com.kirillkotov.tablebuttonsjavafxlect.controllers to javafx.fxml;
    opens com.kirillkotov.tablebuttonsjavafxlect.model to javafx.base;
}