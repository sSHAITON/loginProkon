package com.mycompany.loginprokon;

import com.mycompany.loginprokon.DashboardController.JadwalController;
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
import javafx.scene.layout.AnchorPane;

public class PrimaryController {

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private PasswordField passwordLabel;

    @FXML
    private Label passwordlabel;

    @FXML
    private TextField usernameLabel;

    @FXML
    private Label usernamelabel;

    // Database tool
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void loginAdmin(ActionEvent event) {
        String sql = "SELECT admin.*, guru.Nama, guru.NIP FROM admin JOIN guru ON admin.NIP = guru.NIP WHERE admin.username = ? AND admin.password = ?";
        Connection con = DBConnection.getDBConn();

        try {
            Alert alert;
            String username = usernameLabel.getText();
            String password = passwordLabel.getText();

            prepare = con.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);

            result = prepare.executeQuery();

            if (username.isEmpty() || password.isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Mohon Isi dengan Benar");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    String namaGuru = result.getString("Nama");
                    int nip = result.getInt("NIP");

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
                    dashboardController.displayName(namaGuru, nip);

                    DashboardController.JadwalController jadwalController = dashboardController.new JadwalController();
                    jadwalController.displayNamejadwal(namaGuru, nip);

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
