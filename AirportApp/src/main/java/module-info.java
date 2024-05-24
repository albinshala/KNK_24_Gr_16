module com.example.airportapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.airportapp to javafx.fxml;
    opens controllers to javafx.fxml;

    exports com.example.airportapp;
    exports controllers;
}
