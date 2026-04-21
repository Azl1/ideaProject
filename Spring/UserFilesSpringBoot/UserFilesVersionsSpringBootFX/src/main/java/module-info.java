module com.abdullaevaziz.userfilesversionsspringbootfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires okhttp3;
    requires retrofit2;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires retrofit2.converter.jackson;
    requires static lombok;


    opens com.abdullaevaziz.userfilesversionsspringbootfx to javafx.fxml;
    exports com.abdullaevaziz.userfilesversionsspringbootfx;
    exports com.abdullaevaziz.userfilesversionsspringbootfx.controllers.usercontrollers to javafx.fxml;
    opens com.abdullaevaziz.userfilesversionsspringbootfx.controllers.usercontrollers to javafx.fxml;
    exports com.abdullaevaziz.userfilesversionsspringbootfx.dto to  com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.userfilesversionsspringbootfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.userfilesversionsspringbootfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.userfilesversionsspringbootfx.model to  com.fasterxml.jackson.databind;
}