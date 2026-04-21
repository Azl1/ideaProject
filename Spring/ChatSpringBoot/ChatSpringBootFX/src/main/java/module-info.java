module com.abdullaevaziz.chatspringbootfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.annotation;
    requires java.prefs;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires retrofit2;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires retrofit2.converter.jackson;
    requires okhttp.eventsource;


    opens com.abdullaevaziz.chatspringbootfx to javafx.fxml;
    exports com.abdullaevaziz.chatspringbootfx;
    exports com.abdullaevaziz.chatspringbootfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.chatspringbootfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.chatspringbootfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.chatspringbootfx.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.chatspringbootfx.controllers.usercontrollers to javafx.fxml;
    opens com.abdullaevaziz.chatspringbootfx.controllers.usercontrollers to javafx.fxml;

}