module com.abdullaevaziz.studentsspringbootdataclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires static lombok;
    requires retrofit2;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires retrofit2.converter.jackson;
    requires java.prefs;



    opens com.abdullaevaziz.studentsspringbootdataclient to javafx.fxml;
    exports com.abdullaevaziz.studentsspringbootdataclient;
    exports com.abdullaevaziz.studentsspringbootdataclient.controllers to javafx.fxml;
    opens com.abdullaevaziz.studentsspringbootdataclient.controllers to javafx.fxml;
    exports com.abdullaevaziz.studentsspringbootdataclient.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.studentsspringbootdataclient.dto to  com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.studentsspringbootdataclient.controllers.studentcontrollers to javafx.fxml;
    opens com.abdullaevaziz.studentsspringbootdataclient.controllers.studentcontrollers to javafx.fxml;
    exports com.abdullaevaziz.studentsspringbootdataclient.controllers.autocontrollers to javafx.fxml;
    opens com.abdullaevaziz.studentsspringbootdataclient.controllers.autocontrollers to javafx.fxml;
}