module com.example.studentsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.abdullaevaziz.studentsfx to javafx.fxml;
    exports com.abdullaevaziz.studentsfx;
    exports com.abdullaevaziz.studentsfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.studentsfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.studentsfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.studentsfx.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.studentsfx.controllers.autocontrollers to javafx.fxml;
    opens com.abdullaevaziz.studentsfx.controllers.autocontrollers to javafx.fxml;
    exports com.abdullaevaziz.studentsfx.controllers.studentcontrollers to javafx.fxml;
    opens com.abdullaevaziz.studentsfx.controllers.studentcontrollers to javafx.fxml;
}