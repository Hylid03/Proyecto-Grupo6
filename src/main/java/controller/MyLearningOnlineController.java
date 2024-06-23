package controller;

import data.Courses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

public class MyLearningOnlineController {

    @FXML
    private ListView<Courses> cursoListView;

    private ObservableList<Courses> cursos = FXCollections.observableArrayList(
            //Ejemplos para probar la lista
            new Courses("6", "Desarrollo C++", "Learn C++ programming basics", 2),
            new Courses("5", "Desarrollo Web", "Introduction to web development", 3),
            new Courses("4", "Fundamentos de ciencias de datos", "Fundamental concepts of data science", 4)
    );

    @FXML
    public void initialize() {
        cursoListView.setItems(cursos);
    }

    @FXML
    private void handleInscripcionCurso() {
        Courses cursoSeleccionado = cursoListView.getSelectionModel().getSelectedItem();
        if (cursoSeleccionado != null) {
            String mensaje = String.format("Te has inscrito en el curso:\n\nNombre: %s\nDuración: %s meses\nDescripción: %s\nNivel de dificultad: %d",
                    cursoSeleccionado.getName(), cursoSeleccionado.getDurationInMonths(), cursoSeleccionado.getDescription(), cursoSeleccionado.getLvlOfDificulty());
            showAlert("Inscripción en Curso", mensaje);
        } else {
            showAlert("Error", "Por favor selecciona un curso para inscribirte.");
        }
    }

    @FXML
    private void handleAccesoLecciones() {
        Courses cursoSeleccionado = cursoListView.getSelectionModel().getSelectedItem();
        if (cursoSeleccionado != null) {
            String mensaje = String.format("Accediendo a las lecciones del curso:\n\nNombre: %s\nDuración: %s meses\nDescripción: %s\nNivel de dificultad: %d",
                    cursoSeleccionado.getName(), cursoSeleccionado.getDurationInMonths(), cursoSeleccionado.getDescription(), cursoSeleccionado.getLvlOfDificulty());
            showAlert("Acceso a Lecciones", mensaje);
        } else {
            showAlert("Error", "Por favor selecciona un curso para acceder a las lecciones.");
        }
    }

    @FXML
    private void handleSeguimientoProgreso() {
        StringBuilder mensaje = new StringBuilder("Tu progreso en los cursos:\n\n");
        for (Courses curso : cursos) {
            mensaje.append(String.format("- %s: Completo\n", curso.getName()));
        }
        showAlert("Seguimiento de Progreso", mensaje.toString());
    }
    private void showAlert(String titulo, String mensaje) {
        Alert alert = util.UtilityFX.alert(titulo,mensaje);
    }
}
