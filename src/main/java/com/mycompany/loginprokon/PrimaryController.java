package com.mycompany.loginprokon;

import com.mycompany.loginprokon.data.DBConnection;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PrimaryController {

   @FXML
    private Button loginButton;

    @FXML
    private BorderPane loginPane;

    @FXML
    private PasswordField passwordLabel;

    @FXML
    private Label passwordlabel;

    @FXML
    private TextField usernameLabel;

    @FXML
    private Label usernamelabel;
    
    //Database tool
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
   public void loginAdmin(ActionEvent event) {
    String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
    Connection con = DBConnection.getDBConn();

    try {
        Alert alert;
        String username = usernameLabel.getText();

        prepare = con.prepareStatement(sql);
        prepare.setString(1, usernameLabel.getText());
        prepare.setString(2, passwordLabel.getText());

        result = prepare.executeQuery(); // Inilah bagian yang Anda tanyakan

        if (username.isEmpty() || passwordLabel.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Mohon Isi dengan Benar");
            alert.showAndWait();
        } else {
            if (result.next()) {
                // Dashboard
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Login Berhasil");
                alert.showAndWait();

                loginButton.getScene().getWindow().hide();
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
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Username/Password Salah");
                alert.showAndWait();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

//    @FXML
//    private void handleLoginButtonAction(ActionEvent event) throws IOException {
//        String username = usernameLabel.getText();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
//        Parent root = loader.load();
//
//        DashboardController dashboardController = loader.getController();
//        dashboardController.displayName(username);
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        double width = 920;
//        double height = 640;
//        stage.setWidth(width);
//        stage.setHeight(height);
//        stage.setScene(scene);
//        stage.show();
//    }
//}   