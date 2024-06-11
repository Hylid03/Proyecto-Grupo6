package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    public TextField txf_Name;
    @FXML
    public TextField txf_Password;
    @FXML
    private void login() {
        String username = txf_Name.getText();
        String password = txf_Password.getText();

    }
}