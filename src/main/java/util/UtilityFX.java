package util;

import data.User;
import pyt.gp6.proyectogrupo6.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.Objects;

public class UtilityFX {

    public static void loadPage(String className, String page, BorderPane bp){
        try {
            Class<?> cl = Class.forName(className);
            FXMLLoader fxmlLoader = new FXMLLoader(cl.getResource(page));
            bp.setCenter(fxmlLoader.load());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Alert alert(String title, String headerText){
        Alert myalert = new Alert(Alert.AlertType.INFORMATION);
        myalert.setTitle(title);
        myalert.setHeaderText(headerText);
        DialogPane dialogPane = myalert.getDialogPane();
        String css = Objects.requireNonNull(HelloApplication.class.getResource("dialog.css")).toExternalForm();
        dialogPane.getStylesheets().add(css);
        dialogPane.getStyleClass().add("myDialog");
        return myalert;
    }

    public static TextInputDialog dialog(String title, String headerText){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        String css = Objects.requireNonNull(HelloApplication.class.getResource("dialog.css")).toExternalForm();
        dialog.getEditor().getStylesheets().add(css);
        return dialog;
    }


    public static boolean checkCredentials(String username, String password, User user) {
        if (username == user.getUsername()&&password == user.getUsername()) {
            return true;
        }
        return false;
    }
}
