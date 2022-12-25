module de.example.clicker {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.example.clicker to javafx.fxml;
    exports de.example.clicker;
}