module com.abdullaevaziz.fencingschoolfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires com.fasterxml.jackson.annotation;
    requires static lombok;
    requires retrofit2;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires retrofit2.converter.jackson;
    requires java.prefs;

    opens com.abdullaevaziz.fencingschoolfx to javafx.fxml;
    exports com.abdullaevaziz.fencingschoolfx;
    exports com.abdullaevaziz.fencingschoolfx.controllers.usercontrollers to javafx.fxml;
    opens com.abdullaevaziz.fencingschoolfx.controllers.usercontrollers to javafx.fxml;
    exports com.abdullaevaziz.fencingschoolfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.fencingschoolfx.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.fencingschoolfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.fencingschoolfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.fencingschoolfx.model to javafx.base;
}