module com.example.proyectogrupo6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.mail;
    requires kernel;
    requires layout;


    opens pyt.gp6.proyectogrupo6 to javafx.fxml;
    exports pyt.gp6.proyectogrupo6;
    exports controller;
    opens controller to javafx.fxml;
}