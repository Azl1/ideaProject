module com.abdullaevaziz.tables {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsonschema2pojo.core;
    requires codemodel;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jdk.jshell;
    requires java.desktop;
    requires javax.mail.api;


    opens com.abdullaevaziz.tables to javafx.fxml;
    exports com.abdullaevaziz.tables;
    exports com.abdullaevaziz.tables.controllers to javafx.fxml;
    opens com.abdullaevaziz.tables.controllers to javafx.fxml;
    exports com.abdullaevaziz.modelData to com.fasterxml.jackson.databind;
    opens com.abdullaevaziz.modelData to javafx.base;




}