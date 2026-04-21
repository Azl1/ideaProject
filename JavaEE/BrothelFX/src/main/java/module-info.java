module com.abdullaevaziz.brothelfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;


    opens com.abdullaevaziz.brothelfx to javafx.fxml;
    exports com.abdullaevaziz.brothelfx;
    exports com.abdullaevaziz.brothelfx.controllers to  javafx.fxml;
    opens com.abdullaevaziz.brothelfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.brothelfx.controllers.clientcontrollers to javafx.fxml;
    opens com.abdullaevaziz.brothelfx.controllers.clientcontrollers to javafx.fxml;
    exports com.abdullaevaziz.brothelfx.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.brothelfx.dto;
    exports com.abdullaevaziz.brothelfx.controllers.prostitutecontrollers to  javafx.fxml;
    opens com.abdullaevaziz.brothelfx.controllers.prostitutecontrollers to javafx.fxml;
}