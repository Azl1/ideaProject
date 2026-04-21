module com.abdullaevaziz.centralbankofyekaterinburg {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.abdullaevaziz.main to javafx.fxml;
    exports com.abdullaevaziz.main;
    exports com.abdullaevaziz.controllers to javafx.fxml;
    opens com.abdullaevaziz.controllers to javafx.fxml;
}