package controller;

import data.Courses;
import data.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        TutorColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getTutor().getUsername()));

        courseList = FXCollections.observableArrayList();
        Table.setItems(courseList);

        loadCourses();

        gestionarInscripcionesButton.setOnAction(event -> showAlert("Gestionar Inscripciones", "Gestionar Inscripciones button clicked"));
        notificacionInscripcionesButton.setOnAction(event -> showAlert("Notificación de Inscripciones", "Notificación de Inscripciones button clicked"));
    }

    private void loadCourses() {
        User tutor = new User("john_doe", "password123", "john@example.com", 1);
        courseList.add(new Courses("6 months", "Java Programming", "Learn Java from scratch", 2, tutor));
        courseList.add(new Courses("4 months", "Web Development", "Learn HTML, CSS, and JavaScript", 3, tutor));
    }

    private void showAlert(String title, String content) {
        Alert alert = util.UtilityFX.alert(title, content);
    }
}
