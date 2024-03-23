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
    private void handleLoginButtonAction(ActionEvent event) throws IOException{
        
            String username = usernameLabel.getText();

            // Memuat file FXML dashboard.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            
            DashboardController dashboardController = loader.getController();
            dashboardController.displayName(username);
            // Membuat scene baru
            Scene scene = new Scene(root);
            
            // Mengambil stage (jendela) dari event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Menetapkan scene baru ke stage
            stage.setScene(scene);
            stage.show();
        
    }
}

