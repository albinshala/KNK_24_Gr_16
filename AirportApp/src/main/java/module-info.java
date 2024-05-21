module com.example.airportapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.airportapp to javafx.fxml;
    exports com.example.airportapp;
}