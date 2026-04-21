module com.abdullaevaziz.fencingschoolspringsecurityfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires static lombok;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires retrofit2;
    requires retrofit2.converter.jackson;
    requires jjwt;


    opens com.abdullaevaziz.fencingschoolspringsecurityfx to javafx.fxml;
    exports com.abdullaevaziz.fencingschoolspringsecurityfx;
    exports com.abdullaevaziz.fencingschoolspringsecurityfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.fencingschoolspringsecurityfx.controllers to javafx.fxml;
    exports com.abdullaevaziz.fencingschoolspringsecurityfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.fencingschoolspringsecurityfx.controllers.usercontrollers to javafx.fxml;
    opens com.abdullaevaziz.fencingschoolspringsecurityfx.controllers.usercontrollers to javafx.fxml;
    exports com.abdullaevaziz.fencingschoolspringsecurityfx.model to com.fasterxml.jackson.databind;
    opens com.abdullaevaziz.fencingschoolspringsecurityfx.model to javafx.base;

}