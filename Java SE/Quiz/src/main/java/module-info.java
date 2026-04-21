module com.abdullaevaziz.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires jsonschema2pojo.core;
    requires codemodel;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires org.apache.commons.text;


    opens com.abdullaevaziz.quiz to javafx.fxml;
    exports com.abdullaevaziz.quiz;
    exports com.abdullaevaziz.quiz.controllers to javafx.fxml;
    opens com.abdullaevaziz.quiz.controllers to javafx.fxml;
    exports com.abdullaevaziz.quiz.model to com.fasterxml.jackson.databind;
}