module com.abdullaevaziz.cardfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires java.prefs;


    opens com.abdullaevaziz.cardfxspring to javafx.fxml;
    exports com.abdullaevaziz.cardfxspring;
    exports com.abdullaevaziz.cardfxspring.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.cardfxspring.controllers to javafx.fxml;
    opens com.abdullaevaziz.cardfxspring.controllers to javafx.fxml;
    exports com.abdullaevaziz.cardfxspring.controllers.usercontroller to javafx.fxml;
    opens com.abdullaevaziz.cardfxspring.controllers.usercontroller to javafx.fxml;
    exports com.abdullaevaziz.cardfxspring.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.cardfxspring.controllers.categorycontrollers to javafx.fxml;
    opens com.abdullaevaziz.cardfxspring.controllers.categorycontrollers to javafx.fxml;
    exports com.abdullaevaziz.cardfxspring.controllers.cardcontrollers to javafx.fxml;
    opens com.abdullaevaziz.cardfxspring.controllers.cardcontrollers to javafx.fxml;

}