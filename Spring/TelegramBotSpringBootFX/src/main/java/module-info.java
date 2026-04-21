module com.abdullaevaziz.telegrambotspringbootfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.annotation;
    requires java.prefs;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires okhttp3;
    requires retrofit2;
    requires retrofit2.converter.jackson;


    opens com.abdullaevaziz.telegrambotspringbootfx to javafx.fxml;
    exports com.abdullaevaziz.telegrambotspringbootfx;
    exports com.abdullaevaziz.telegrambotspringbootfx.controllers;
    opens com.abdullaevaziz.telegrambotspringbootfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.telegrambotspringbootfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.telegrambotspringbootfx.model to com.fasterxml.jackson.databind;
}