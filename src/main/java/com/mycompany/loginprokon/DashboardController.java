/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loginprokon;

import com.mycompany.loginprokon.model.Acara;
import java.io.IOException;
import java.sql.SQLException;

import com.dlsc.gemsfx.daterange.DateRange;
import com.dlsc.gemsfx.daterange.DateRangePicker;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Date;
import java.util.List;

import com.mycompany.loginprokon.data.AppQuery;
import com.mycompany.loginprokon.data.DBConnection;

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

    @FXML
    private ComboBox<String> semesterComboBox;

    @FXML
    private TableView<Acara> tableViewKalender;

    @FXML
    private TableColumn<Acara, String> keteranganAcaraColumn;

    @FXML
    private TableColumn<Acara, String> semesterColumn;

    @FXML
    private TableColumn<Acara, Date> tanggalKalenderColumn;

    @FXML
    private TextField ketAcara;

    @FXML
    private DateRangePicker tanggalKalenderAkademik;

    @FXML
    private Button addBtnKalender;

    @FXML
    private Button deleteBtnKalender;

    @FXML
    private Button jadwalpelBtn;

    @FXML
    private AnchorPane daftarguru;

    @FXML
    private Button daftarguruBtn;

    @FXML
    private Button daftarsiswBtn;

    @FXML
    private AnchorPane daftarsiswa;

    // inisialisasi method
    // setiap method yang digunakkan masukkan di sini
    public void initialize() {
        initializeKalenderAkademik();

    }

    // navbar
    public void switchForm(ActionEvent event) {
        dashboard.setVisible(false);
        jadwal.setVisible(false);
        kalender.setVisible(false);
        nilaiRapot.setVisible(false);
        monitoringHaf.setVisible(false);
        daftarguru.setVisible(false);
        daftarsiswa.setVisible(false);

        dashboardBtn.getStyleClass().remove("navbtn-selected");
        jadwalBtn.getStyleClass().remove("navbtn-selected");
        kalenderBtn.getStyleClass().remove("navbtn-selected");
        nilaiRapotBtn.getStyleClass().remove("navbtn-selected");
        monitoringHafBtn.getStyleClass().remove("navbtn-selected");
        try {
            if (event.getSource() == dashboardBtn) {
                dashboard.setVisible(true);
                dashboardBtn.getStyleClass().add("navbtn-selected");
            } else if (event.getSource() == jadwalBtn || event.getSource() == jadwalpelBtn) {
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
            } else if (event.getSource() == daftarguruBtn) {
                daftarguru.setVisible(true);
            } else if (event.getSource() == daftarsiswBtn) {
                daftarsiswa.setVisible(true);
            }
        } finally {
            try {
                List<Acara> acaraList = AppQuery.loadAcaraFromDatabase();
                tableViewKalender.getItems().setAll(acaraList);
            } catch (SQLException el) {
                System.out.println("Failed to load acara from database: " + el.getMessage());

            }
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

    public void initializeKalenderAkademik() {
        semesterComboBox.getItems().add("Semester Ganjil");
        semesterComboBox.getItems().add("Semester Genap");
        keteranganAcaraColumn.setCellValueFactory(new PropertyValueFactory<>("keteranganAcara"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        tanggalKalenderColumn.setCellValueFactory(new PropertyValueFactory<>("tanggalAsString"));

        try {
            List<Acara> acaraList = AppQuery.loadAcaraFromDatabase();
            tableViewKalender.getItems().setAll(acaraList);
        } catch (SQLException e) {
            System.out.println("Failed to load acara from database: " + e.getMessage());
        }

        addBtnKalender.setOnAction(event -> {
            String keteranganAcara = ketAcara.getText();
            String semester = semesterComboBox.getValue();
            DateRange tanggal = tanggalKalenderAkademik.getValue();

            Acara acara = new Acara(keteranganAcara, semester, tanggal);
            tableViewKalender.getItems().add(acara);

            try {
                AppQuery.insertAcara(acara);
            } catch (SQLException e) {
                System.out.println("Failed to insert acara: " + e.getMessage());
            }
        });

        deleteBtnKalender.setOnAction(event -> {
            Acara selectedAcara = tableViewKalender.getSelectionModel().getSelectedItem();
            if (selectedAcara != null) {
                tableViewKalender.getItems().remove(selectedAcara);
                try {
                    AppQuery.deleteAcara(selectedAcara);
                } catch (SQLException e) {
                    System.out.println("Failed to delete acara: " + e.getMessage());
                }
            }
        });
    }
    // end of kalender

    // Nilai Rapot

    // end of nilai rapot

    // Monitoring Hafalan

    // end of monitoring hafalan
}
