/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loginprokon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private Button logoutButton;
    @FXML
    Label nameLabel;
    @FXML
    private AnchorPane dashboard;

    @FXML
    private Button dashboardBtn;

    @FXML
    private StackPane dashboardPane;

    @FXML
    private Button grafikHafBtn;

    @FXML
    private AnchorPane jadwal;

    @FXML
    private Button jadwalBtn;

    @FXML
    private AnchorPane kalender;

    @FXML
    private Button kalenderBtn;

    @FXML
    private AnchorPane monitoringHaf;

    @FXML
    private Button monitoringHafBtn;

    @FXML
    private AnchorPane nilaiRapot;

    @FXML
    private Button nilaiRapotBtn;

    // navbar
    public void switchForm(ActionEvent event) {
        dashboard.setVisible(false);
        jadwal.setVisible(false);
        kalender.setVisible(false);
        nilaiRapot.setVisible(false);
        monitoringHaf.setVisible(false);

        dashboardBtn.getStyleClass().remove("navbtn-selected");
        jadwalBtn.getStyleClass().remove("navbtn-selected");
        kalenderBtn.getStyleClass().remove("navbtn-selected");
        nilaiRapotBtn.getStyleClass().remove("navbtn-selected");
        monitoringHafBtn.getStyleClass().remove("navbtn-selected");

        if (event.getSource() == dashboardBtn) {
            dashboard.setVisible(true);
            dashboardBtn.getStyleClass().add("navbtn-selected");
        } else if (event.getSource() == jadwalBtn) {
            jadwal.setVisible(true);
            jadwalBtn.getStyleClass().add("navbtn-selected");
        } else if (event.getSource() == kalenderBtn) {
            kalender.setVisible(true);
            kalenderBtn.getStyleClass().add("navbtn-selected");
        } else if (event.getSource() == nilaiRapotBtn) {
            nilaiRapot.setVisible(true);
            nilaiRapotBtn.getStyleClass().add("navbtn-selected");
        } else if (event.getSource() == monitoringHafBtn) {
            monitoringHaf.setVisible(true);
            monitoringHafBtn.getStyleClass().add("navbtn-selected");
        }
    }

    @FXML
    public void displayName(String username) {
        nameLabel.setText("Selamat Datang, " + username + "!");
    }

    public void handleLogoutButtonAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Logout");
        alert.setContentText("Apakah anda ingin Logout ?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Primary.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    // End of navbar

    // Dashboard

    // end of dashboard

    // Jadwal

    // end of jadwal

    // Kalender

    // end of kalender

    // Nilai Rapot

    // end of nilai rapot

    // Monitoring Hafalan

    // end of monitoring hafalan
}
