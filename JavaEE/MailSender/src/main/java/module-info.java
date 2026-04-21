module com.abdullaevaziz.mailsender {
    requires javafx.controls;
    requires javafx.fxml;
    requires codemodel;
    requires jsonschema2pojo.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires javax.mail.api;


    opens com.abdullaevaziz.app to javafx.fxml;
    exports com.abdullaevaziz.app;
    exports com.abdullaevaziz.controllers to javafx.fxml;
    opens com.abdullaevaziz.controllers to javafx.fxml;

}