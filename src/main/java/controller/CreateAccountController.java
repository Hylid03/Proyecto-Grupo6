package controller;

import data.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class CreateAccountController {

    private List<User> userList = new ArrayList<>();

    @FXML
    private TextField txf_Name;

    @FXML
    private PasswordField txf_Password;

    @FXML
    private TextField txf_Email;

    @FXML
    private void createAccountButtonClicked() {
        String username = txf_Name.getText();
        String password = txf_Password.getText();
        String email = txf_Email.getText();

        boolean accountCreated = registerAccount(username, password, email);
        if (accountCreated) {
            showAlert("Cuenta creada", "La cuenta se ha creado correctamente.");
        } else {
            showAlert("Error", "Hubo un problema al crear la cuenta.");
        }
    }

    private boolean registerAccount(String username, String password, String email) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                showAlert("Error", "El nombre de usuario ya está en uso.");
                return false;
            }
        }
        User newUser = new User(username, password, email, 0);
        /*
        Tipos de roles:
            -Administrador: 0
            -Instructor: 1
            -Usuario: 2
        */
        userList.add(newUser);
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = util.UtilityFX.alert(title,message);
    }
}