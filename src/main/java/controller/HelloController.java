package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import java.io.IOException;

public class HelloController {

    @FXML
    private TextField txf_Name;

    @FXML
    private TextField txf_Password;

    @FXML
    private AnchorPane aP;

    @FXML
    private void buttonOnAction(ActionEvent actionEvent) {
        String password = txf_Password.getText();
        try {
            if (password.contains("A")) {
                loadPage("lessonMaintaining.fxml");
            } else if (password.contains("B")) {
                loadPage("courseMaintaining.fxml");
            } else if (password.contains("C")) {
                loadPage("inscriptionMaintaining.fxml");
            } else {
                loadPage("myLearningOnline.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la traza de la excepción para depuración
            throw new RuntimeException("Error al cargar la página correspondiente", e);
        }
    }

    @FXML
    private void createAccountOnAction(ActionEvent actionEvent) {
        try {
            loadPage("CreateAccount.fxml");
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la traza de la excepción para depuración
            throw new RuntimeException("Error al cargar la página correspondiente", e);
        }
    }

    private void loadPage(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/" + fxmlFile));
        AnchorPane newPage = fxmlLoader.load();

        if (aP == null) {
            throw new IllegalStateException("AnchorPane 'aP' is null. Check FXML file.");
        }

        aP.getChildren().setAll(newPage);
    }
}
