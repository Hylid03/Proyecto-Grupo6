module com.example.proyectogrupo6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.mail;


    opens com.example.proyectogrupo6 to javafx.fxml;
    exports com.example.proyectogrupo6;
    exports controller;
    opens controller to javafx.fxml;
}