module com.abdullaevaziz.userfilesspringbootfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.prefs;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires retrofit2.converter.jackson;
    requires retrofit2;


    opens com.abdullaevaziz.userfilesspringbootfx to javafx.fxml;
    exports com.abdullaevaziz.userfilesspringbootfx;
    exports com.abdullaevaziz.userfilesspringbootfx.controllers.usercontrollers to javafx.fxml;
    opens com.abdullaevaziz.userfilesspringbootfx.controllers.usercontrollers to javafx.fxml;
    exports com.abdullaevaziz.userfilesspringbootfx.dto to  com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.userfilesspringbootfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.userfilesspringbootfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.userfilesspringbootfx.model to  com.fasterxml.jackson.databind;
}