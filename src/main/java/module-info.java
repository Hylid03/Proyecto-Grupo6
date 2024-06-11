module com.example.proyectogrupo6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectogrupo6 to javafx.fxml;
    exports com.example.proyectogrupo6;
    exports controller;
    opens controller to javafx.fxml;
}