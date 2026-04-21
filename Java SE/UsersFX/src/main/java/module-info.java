module com.abdullaevaziz.combobox {
    requires javafx.controls;
    requires javafx.fxml;
    requires jsonschema2pojo.core;
    requires codemodel;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    opens com.abdullaevaziz.combobox to javafx.fxml;
    exports com.abdullaevaziz.combobox;
    exports com.abdullaevaziz.controllers to javafx.fxml;
    exports com.abdullaevaziz.model to com.fasterxml.jackson.databind;
    opens com.abdullaevaziz.controllers to javafx.fxml;
}