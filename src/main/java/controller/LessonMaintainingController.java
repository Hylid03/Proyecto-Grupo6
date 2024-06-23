package controller;

import data.Lessons;
import domain.tree.BST;
import domain.tree.TreeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

public class LessonMaintainingController {

    private BST lessonTree; // Cambiado a BST
    private TableView<Lessons> lessonTableView;

    public LessonMaintainingController(BST lessonTree, TableView<Lessons> lessonTableView) {
        this.lessonTree = lessonTree;
        this.lessonTableView = lessonTableView;
    }

    public void addLesson(String title, String content) throws TreeException {
        Lessons newLesson = new Lessons(title, content);
        lessonTree.add(newLesson);
        refreshTable();
    }

    public void editLesson(Lessons lesson, String newContent) throws TreeException {
        lesson.setContent(newContent);
        refreshTable();
    }

    public void deleteLesson(Lessons lesson) throws TreeException {
        lessonTree.remove(lesson);
        refreshTable();
    }

    public void displayLessons() throws TreeException {
        ObservableList<Lessons> lessonList = FXCollections.observableArrayList();
        lessonTableView.setItems(lessonList);
    }

    private void refreshTable() throws TreeException {
        displayLessons();
        showAlert("Operación completada", "Se ha actualizado la lista de lecciones.");
    }

    @FXML
    public void crearLeccion() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Crear Nueva Lección");
        dialog.setHeaderText("Ingrese el título y el contenido de la nueva lección:");

        dialog.showAndWait().ifPresent(result -> {
            String title = dialog.getEditor().getText();
            String content = result.trim();
            if (!title.isEmpty() && !content.isEmpty()) {
                try {
                    addLesson(title, content);
                } catch (TreeException e) {
                    throw new RuntimeException(e);
                }
            } else {
                showAlert("Error", "Por favor, complete todos los campos.");
            }
        });
    }

    @FXML
    public void editarLeccion() {
        Lessons selectedLesson = lessonTableView.getSelectionModel().getSelectedItem();

        if (selectedLesson != null) {
            TextInputDialog dialog = new TextInputDialog(selectedLesson.getContent());
            dialog.setTitle("Editar Lección");
            dialog.setHeaderText("Editar el contenido de la lección:");

            dialog.showAndWait().ifPresent(newContent -> {
                try {
                    editLesson(selectedLesson, newContent);
                } catch (TreeException e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            showAlert("Error", "Seleccione una lección para editar.");
        }
    }

    @FXML
    public void eliminarLeccion() {
        Lessons selectedLesson = lessonTableView.getSelectionModel().getSelectedItem();

        if (selectedLesson != null) {
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Eliminar Lección");
            confirmDialog.setHeaderText("¿Está seguro que desea eliminar la lección seleccionada?");
            confirmDialog.setContentText("Esta acción no se puede deshacer.");

            confirmDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        deleteLesson(selectedLesson);
                    } catch (TreeException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            showAlert("Error", "Seleccione una lección para eliminar.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = util.UtilityFX.alert(title, message);
        alert.show();
    }
}