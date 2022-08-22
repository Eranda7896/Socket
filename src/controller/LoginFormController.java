package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public TextField txtUname;
    public PasswordField txtPname;
    public AnchorPane LoginFormContext;

    public void OpenClientForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/ClientForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window =(Stage) LoginFormContext.getScene().getWindow();
        window.setTitle("Login");

        if (txtUname.getText().equalsIgnoreCase("eranda") && txtPname.getText().equalsIgnoreCase("123")){
            window.setScene(new Scene(load));
            window.centerOnScreen();
        }else {
            new Alert(Alert.AlertType.WARNING,"Username or password incorrect").show();
        }


    }
}
