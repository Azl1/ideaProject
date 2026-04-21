module com.kirillkotov.opennewformsjavafxlect {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kirillkotov.opennewformsjavafxlect to javafx.fxml;
    exports com.kirillkotov.opennewformsjavafxlect;
    exports com.kirillkotov.opennewformsjavafxlect.controllers;
    opens com.kirillkotov.opennewformsjavafxlect.controllers to javafx.fxml;
}