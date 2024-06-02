/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loginprokon;

import com.mycompany.loginprokon.model.Acara;
import com.mycompany.loginprokon.model.Nilai;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;
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
    private Label labelUser;     
    
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
    private TextField namaNilai;
    
    @FXML
    private TextField nisNilai;
    
    @FXML
    private TextField smtNilai;
    
    @FXML
    private TextField nilaiNilai;
    
    @FXML
    private Button addBtnNilai;
    
    @FXML
    private Button upBtnNilai;
    
    @FXML
    private Button clrBtnNilai;
    
    @FXML
    private Button dltBtnNilai;
    
    @FXML
    private Button pdfBtnNilai;

    @FXML
    private ComboBox<String> ComboBoxNilai;
    
    @FXML
    private TableColumn<Nilai, String> ketNilaiColumn;

    @FXML
    private TableColumn<Nilai, String> kkmColumn;
    
    @FXML
    private TableColumn<Nilai, String> nilaiColumn;
    
    @FXML
    private TableColumn<Nilai, String> mapelNilaiColumn;
    
    @FXML
    private TableView<Nilai> tableViewNilai;
    
    @FXML
    private TextField searchField;
    
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
        initializeNilai();
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
    public void initializeNilai() {
        ComboBoxNilai.getItems().add("Tajwid");
        ComboBoxNilai.getItems().add("Hafalan Surat Pendek");
        ComboBoxNilai.getItems().add("Sejarah Kebudayaan Islam");
        mapelNilaiColumn.setCellValueFactory(new PropertyValueFactory<>("mataPelajaran"));
        ketNilaiColumn.setCellValueFactory(new PropertyValueFactory<>("ketSiswa"));
        nilaiColumn.setCellValueFactory(new PropertyValueFactory<>("nilaiSiswa"));
        kkmColumn.setCellValueFactory(new PropertyValueFactory<>("kkmSiswa"));

        // Fungsi untuk menginisialisasi data nilai dari database dan mengatur aksi untuk tombol tambah dan hapus nilai
        try {
            List<Nilai> nilaiList = AppQuery.loadNilaiFromDatabase(""); // Empty string for no initial filter
            tableViewNilai.getItems().setAll(nilaiList);
        } catch (SQLException e) {
            System.out.println("Failed to load nilai from database: " + e.getMessage());
        }

        addBtnNilai.setOnAction(event -> {
            String nama = namaNilai.getText();
            String nis = nisNilai.getText();
            String semester = smtNilai.getText();
            String mataPelajaran = ComboBoxNilai.getValue();
            String nilaiSiswa = nilaiNilai.getText();
            String kkmSiswa = "75";
            String ketSiswa = "Hebat";

            Nilai nilai = new Nilai(nama, nis, semester, mataPelajaran, nilaiSiswa, kkmSiswa, ketSiswa);

            try {
                AppQuery.addNilai(nilai);
                tableViewNilai.getItems().add(nilai); // Add to table only if insertion is successful
            } catch (SQLException e) {
                System.out.println("Failed to insert nilai: " + e.getMessage());
                // Display error message to the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Nilai Insertion Failed");
                alert.setContentText("Failed to add nilai: " + e.getMessage());
                alert.showAndWait();
            }
        });

        clrBtnNilai.setOnAction(event -> {
            try {
                AppQuery.clrNilai();
                // Clear the table view to reflect the deletion
                tableViewNilai.getItems().clear();
            } catch (SQLException e) {
                System.out.println("Failed to delete all nilai: " + e.getMessage());
                // Display error message to the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Nilai Deletion Failed");
                alert.setContentText("Failed to delete all nilai: " + e.getMessage());
                alert.showAndWait();
            }
        });
        
        dltBtnNilai.setOnAction(event -> {
            Nilai selectedNilai = tableViewNilai.getSelectionModel().getSelectedItem();
            if (selectedNilai != null) {
                tableViewNilai.getItems().remove(selectedNilai);
                try {
                    AppQuery.dltNilai(selectedNilai);
                } catch (SQLException e) {
                    System.out.println("Failed to delete nilai: " + e.getMessage());
                }
            }
        });
        
        upBtnNilai.setOnAction(event -> {
            Nilai selectedNilai = tableViewNilai.getSelectionModel().getSelectedItem();
            if (selectedNilai != null) {
                selectedNilai.setNilaiSiswa(nilaiNilai.getText());
//                selectedNilai.setKkmSiswa(kkmNilai.getText());
//                selectedNilai.setKetSiswa(ketNilai.getText());

                try {
                    AppQuery.upNilai(selectedNilai);
                    tableViewNilai.refresh(); // Refresh TableView untuk menampilkan data terbaru
                } catch (SQLException e) {
                    System.out.println("Failed to update nilai: " + e.getMessage());
                }
            } else {
                // Tampilkan pesan bahwa tidak ada baris yang dipilih
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setHeaderText("Baris Tidak Dipilih");
                alert.setContentText("Pilih baris untuk memperbarui nilai.");
                alert.showAndWait();
            }
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        try {
            List<Nilai> allNilaiList = AppQuery.loadNilaiFromDatabase("");
            List<Nilai> filteredNilaiList = allNilaiList.stream()
                    .filter(nilai ->
                            nilai.getNama().toLowerCase().contains(newValue.toLowerCase())
                    )
                    .collect(Collectors.toList());
            tableViewNilai.getItems().setAll(filteredNilaiList);
        } catch (SQLException e) {
            System.out.println("Failed to search nilai: " + e.getMessage());
        }
    });
    }
}

    // end of nilai rapot

    // Monitoring Hafalan

    // end of monitoring hafalan
       
