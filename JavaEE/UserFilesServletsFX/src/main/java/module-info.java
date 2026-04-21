module com.example.userfilesservletsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires com.fasterxml.jackson.databind;


    opens com.abdullaevaziz.userfilesservletsfx to javafx.fxml;
    exports com.abdullaevaziz.userfilesservletsfx;
    exports com.abdullaevaziz.userfilesservletsfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.userfilesservletsfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.userfilesservletsfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.userfilesservletsfx.model to com.fasterxml.jackson.databind;

}