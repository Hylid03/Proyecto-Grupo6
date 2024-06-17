package controller;

import com.example.proyectogrupo6.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {
    @FXML
    public TextField txf_Name;
    @FXML
    public TextField txf_Password;
    private AnchorPane ap;
    private void loadPage(String page) {
        FXMLLoader fxmlLoader= new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.ap.setCenterShape(fxmlLoader.load());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void login() {
        String username = txf_Name.getText();
        String password = txf_Password.getText();
    }
    @FXML
    private void buttonOnAction(ActionEvent actionEvent) {
        if (1) { //TODO fix this
            loadPage("lessonMaintaining.fxml");
        }else if (2){
            loadPage("courseMaintaining.fxml");
        }else if (3){
            loadPage("inscriptionMaintaining.fxml");
        }else{
            loadPage("myLearningOnline.fxml");
        }
    }
}