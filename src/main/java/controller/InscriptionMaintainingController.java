package controller;

import data.Courses;
import data.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;

public class InscriptionMaintainingController {

    @FXML
    private TableView<Courses> Table;

    @FXML
    private TableColumn<Courses, String> CoursesColumn;

    @FXML
    private TableColumn<Courses, String> TutorColumn;

    @FXML
    private Button gestionarInscripcionesButton;

    @FXML
    private Button notificacionInscripcionesButton;

    private ObservableList<Courses> courseList;

    @FXML
    public void initialize() {
        CoursesColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set the cell value factory for TutorColumn to display the tutor's username
        TutorColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Courses, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Courses, String> p) {
                return new SimpleStringProperty(p.getValue().getTutor().getUsername());
            }
        });

        courseList = FXCollections.observableArrayList();
        Table.setItems(courseList);

        // Load initial data
        loadCourses();

        // Button actions
        gestionarInscripcionesButton.setOnAction(event -> handleGestionarInscripciones());
        notificacionInscripcionesButton.setOnAction(event -> handleNotificacionInscripciones());
    }

    private void loadCourses() {
        // Sample data
        User tutor = new User("john_doe", "password123", "john@example.com", 1);
        Courses course1 = new Courses("6 months", "Java Programming", "Learn Java from scratch", 2, tutor);
        Courses course2 = new Courses("4 months", "Web Development", "Learn HTML, CSS, and JavaScript", 3, tutor);

        courseList.addAll(course1, course2);
    }

    private void handleGestionarInscripciones() {
        // Handle gestionar inscripciones button action
        System.out.println("Gestionar Inscripciones button clicked");
    }

    private void handleNotificacionInscripciones() {
        // Handle notificación inscripciones button action
        System.out.println("Notificación de Inscripciones button clicked");
    }
}