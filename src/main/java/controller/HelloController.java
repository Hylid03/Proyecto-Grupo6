package controller;

import domain.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pyt.gp6.proyectogrupo6.HelloApplication;
import domain.DataManagement;
import java.io.IOException;

public class HelloController {

    @FXML
    private TextField txf_Name;

    @FXML
    private TextField txf_Password;
    private DataManagement dataManagement;

    @FXML
    private AnchorPane aP;
    public HelloController() {
        try {
            this.dataManagement = new DataManagement("/userData");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to initialize data management.");
        }
    }
    @FXML
    private void buttonOnAction(ActionEvent actionEvent) {
        String username = txf_Name.getText();
        String password = txf_Password.getText();

        try {
            String storedEncryptedPassword = dataManagement.getElementValue(username);
            int role = dataManagement.getRole(username);
            String enteredEncryptedPassword = Encryption.encryptPassword(password);

            if (enteredEncryptedPassword.equals(storedEncryptedPassword)) {
                String roleDescription = getRoleDescription(role);
                showAlert("Login Successful", "Welcome, " + username + "! Role: " + roleDescription);
                // Navigate to next screen or perform action based on role
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            showAlert("Login Failed", "User not found.");
        }
    }

    private String getRoleDescription(int role) {
        switch (role) {
            case 0:
                return "Admin";
            case 1:
                return "Tutor";
            case 2:
                return "Student";
            default:
                return "Unknown";
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

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource( fxmlFile));
        AnchorPane newPage = fxmlLoader.load();

        if (aP == null) {
            throw new IllegalStateException("AnchorPane 'aP' is null. Check FXML file.");
        }

        aP.getChildren().setAll(newPage);
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
