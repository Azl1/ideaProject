module com.abdullaevaziz.quizspringbootfx {
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


    opens com.abdullaevaziz.quizspringbootfx to javafx.fxml;
    exports com.abdullaevaziz.quizspringbootfx;
    exports com.abdullaevaziz.quizspringbootfx.controllers.usercontrollers to javafx.fxml;
    opens com.abdullaevaziz.quizspringbootfx.controllers.usercontrollers to javafx.fxml;
    exports com.abdullaevaziz.quizspringbootfx.dto to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.quizspringbootfx.model to com.fasterxml.jackson.databind;
    exports com.abdullaevaziz.quizspringbootfx.controllers to javafx.fxml;
    opens com.abdullaevaziz.quizspringbootfx.controllers to javafx.fxml;
}