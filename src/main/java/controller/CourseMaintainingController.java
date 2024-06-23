package controller;

import data.Courses;
import domain.tree.AVL;
import domain.tree.TreeException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class CourseMaintainingController {

    @FXML
    private TableView<Courses> cursoTableView;
    @FXML
    private TableColumn<Courses, String> nombreColumn;
    @FXML
    private TableColumn<Courses, String> descripcionColumn;
    @FXML
    private TableColumn<Courses, Integer> duracionColumn;
    @FXML
    private TableColumn<Courses, Integer> nivelColumn;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField duracionField;
    @FXML
    private TextField nivelField;
    @FXML
    private AVL avlTree;
    private Courses cursoSeleccionado;

    public void initialize() {
        //Inicializamos las columnas
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        duracionColumn.setCellValueFactory(new PropertyValueFactory<>("durationInMonths"));
        nivelColumn.setCellValueFactory(new PropertyValueFactory<>("lvlOfDifficulty"));
        avlTree = new AVL();
    }

    @FXML
    private void agregarCurso() {
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        String duracion = duracionField.getText();
        int nivel = Integer.parseInt(nivelField.getText());

        Courses nuevoCurso = new Courses(duracion, nombre, descripcion, nivel);
        avlTree.add(nuevoCurso);
        limpiarCampos();
        cursoTableView.getItems().add(nuevoCurso);
    }

    @FXML
    private void eliminarCurso() throws TreeException {
        Courses cursoSeleccionado = cursoTableView.getSelectionModel().getSelectedItem();
        if (cursoSeleccionado != null) {
            avlTree.remove(cursoSeleccionado);
            cursoTableView.getItems().remove(cursoSeleccionado);
        } else {
            mostrarAlerta("Seleccionar Curso", "Por favor, selecciona un curso para eliminar.");
        }
    }
    @FXML
    private void editarCurso() throws TreeException {
        if (cursoSeleccionado != null) {
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            String duracion = duracionField.getText();
            int nivel = Integer.parseInt(nivelField.getText());
            Courses cursoActualizado = new Courses(duracion, nombre, descripcion, nivel);
            avlTree.remove(cursoSeleccionado);
            avlTree.add(cursoActualizado);
            cursoTableView.getItems().remove(cursoSeleccionado);
            cursoTableView.getItems().add(cursoActualizado);
            limpiarCampos();
            cursoSeleccionado = null;
        } else {
            mostrarAlerta("Seleccionar Curso", "Por favor, selecciona un curso para editar.");
        }
    }
    @FXML
    private void mantenerLecciones() throws IOException{
        loadPage("lessonMaintaining.fxml");
    }

    @FXML
    private void limpiarCampos() {
        nombreField.clear();
        descripcionField.clear();
        duracionField.clear();
        nivelField.clear();
    }
    AnchorPane aP;
    private void loadPage(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/" + fxmlFile));
        AnchorPane newPage = fxmlLoader.load();

        if (aP == null) {
            throw new IllegalStateException("AnchorPane 'aP' is null. Check FXML file.");
        }
        aP.getChildren().setAll(newPage);
    }

    public void mostrarAlerta(String title, String headerText) {
        Alert alert = util.UtilityFX.alert(title, headerText);
        alert.showAndWait();
    }
}