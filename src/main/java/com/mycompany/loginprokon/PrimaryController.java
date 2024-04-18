package com.mycompany.loginprokon;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameLabel;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = usernameLabel.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = loader.load();

        DashboardController dashboardController = loader.getController();
        dashboardController.displayName(username);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double width = 920;
        double height = 640;
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setScene(scene);
        stage.show();
    }

}
