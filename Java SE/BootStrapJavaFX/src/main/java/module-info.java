module com.example.bootstrapjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.bootstrapjavafx to javafx.fxml;
    exports com.example.bootstrapjavafx;
}