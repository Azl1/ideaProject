module com.abdullaevaziz.calculatorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.abdullaevaziz.calculatorfx to javafx.fxml;
    exports com.abdullaevaziz.calculatorfx;
    exports com.abdullaevaziz.calculatorfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.calculatorfx.controllers to javafx.fxml;
}