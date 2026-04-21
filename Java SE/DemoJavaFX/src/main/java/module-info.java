module com.abdullaevaziz.demojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.abdullaevaziz.demojavafx to javafx.fxml;
    exports com.abdullaevaziz.demojavafx;
    exports com.abdullaevaziz.demojavafx.controllers to javafx.fxml;
    opens com.abdullaevaziz.demojavafx.controllers to javafx.fxml;
}