module com.abdullaevaziz.cardfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires static lombok;
    requires com.fasterxml.jackson.databind;


    opens com.abdullaevaziz.cardfx to javafx.fxml;
    exports com.abdullaevaziz.cardfx;
    exports com.abdullaevaziz.cardfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.cardfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.cardfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.cardfx.controllers.usercontroller to javafx.fxml;
    opens com.abdullaevaziz.cardfx.controllers.usercontroller to javafx.fxml;
    exports com.abdullaevaziz.cardfx.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.cardfx.controllers.categorycontrollers to javafx.fxml;
    opens com.abdullaevaziz.cardfx.controllers.categorycontrollers to javafx.fxml;
    exports com.abdullaevaziz.cardfx.controllers.cardcontrollers to javafx.fxml;
    opens com.abdullaevaziz.cardfx.controllers.cardcontrollers to javafx.fxml;

}