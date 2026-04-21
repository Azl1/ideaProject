module com.abdullaevaziz.fileuploaderspringbootfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires okhttp3;
    requires static lombok;
    requires jjwt;
    requires retrofit2;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires retrofit2.converter.jackson;


    opens com.abdullaevaziz.fileuploaderspringbootfx to javafx.fxml;
    exports com.abdullaevaziz.fileuploaderspringbootfx;
    exports com.abdullaevaziz.fileuploaderspringbootfx.controllers.usercontrollers to javafx.fxml;
    opens com.abdullaevaziz.fileuploaderspringbootfx.controllers.usercontrollers to javafx.fxml;
    exports com.abdullaevaziz.fileuploaderspringbootfx.dto to com.fasterxml.jackson.databind;
    opens com.abdullaevaziz.fileuploaderspringbootfx.model to com.fasterxml.jackson.databind;
    opens com.abdullaevaziz.fileuploaderspringbootfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.fileuploaderspringbootfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.fileuploaderspringbootfx.controllers to javafx.fxml;
}