module com.example.bmicalcjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.bmicalcjavafx to javafx.fxml;
    exports com.example.bmicalcjavafx;
}