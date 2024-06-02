/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.loginprokon;

/**
 *
 * @author asepm
 */

import com.mycompany.loginprokon.model.Acara;
import com.mycompany.loginprokon.model.Jadwal;
import com.mycompany.loginprokon.model.Siswa;
import com.mycompany.loginprokon.model.Guru;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.dlsc.gemsfx.TimePicker;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mycompany.loginprokon.data.AppQuery;
import com.mycompany.loginprokon.data.DBConnection;
import javafx.collections.ObservableList;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

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

    @FXML
    private Button addBtnJadwal;

    @FXML
    private ComboBox<String> hariComboBox;

    @FXML
    private ComboBox<String> jadwalComboBox;

    @FXML
    private TableView<Jadwal> jumatJadwalTabelView;

    @FXML
    private TableColumn<Jadwal, String> jumatJadwalTable;

    @FXML
    private TableColumn<Jadwal, String> jumatKelas;

    @FXML
    private TableColumn<Jadwal, String> jumatMapel;

    @FXML
    private TableColumn<Jadwal, String> jumatPukul;

    @FXML
    private TableView<Jadwal> kamisJadwalTabelView;

    @FXML
    private TableColumn<Jadwal, String> kamisJadwalTable;

    @FXML
    private TableColumn<Jadwal, String> kamisKelas;

    @FXML
    private TableColumn<Jadwal, String> kamisMapel;

    @FXML
    private TableColumn<Jadwal, String> kamisPukul;

    @FXML
    private ComboBox<String> kelasComboBox;

    @FXML
    private TableColumn<Jadwal, String> sabtuKelas;

    @FXML
    private TextField nipGurujadwal;

    @FXML
    private Button pdfBtnJadwal;

    @FXML
    private TimePicker pukulColumn;

    @FXML
    private TableView<Jadwal> rabuJadwalTabelView;

    @FXML
    private TableColumn<Jadwal, String> rabuJadwalTable;

    @FXML
    private TableColumn<Jadwal, String> rabuKelas;

    @FXML
    private TableColumn<Jadwal, String> rabuMapel;

    @FXML
    private TableColumn<Jadwal, String> rabuPukul;

    @FXML
    private TableView<Jadwal> sabtuJadwalTabelView;

    @FXML
    private TableColumn<Jadwal, String> sabtuJadwalTable;

    @FXML
    private TableColumn<Jadwal, String> sabtuMapel;

    @FXML
    private TableColumn<Jadwal, String> sabtuPukul;

    @FXML
    private TableView<Jadwal> selasaJadwalTabelView;

    @FXML
    private TableColumn<Jadwal, String> selasaJadwalTable;

    @FXML
    private TableColumn<Jadwal, String> selasaKelas;

    @FXML
    private TableColumn<Jadwal, String> selasaMapel;

    @FXML
    private TableColumn<Jadwal, String> selasaPukul;

    @FXML
    private TableColumn<Jadwal, String> seninJadwalTable;

    @FXML
    private TableView<Jadwal> seninJadwalTableView;

    @FXML
    private TableColumn<Jadwal, String> seninKelas;

    @FXML
    private TableColumn<Jadwal, String> seninMapel;

    @FXML
    private TableColumn<Jadwal, String> seninPukul;

    @FXML
    private Button updateBtnJadwal;

    @FXML
    private Button deleteBtnJadwal;

    @FXML
    private Button clearBtnJadwal;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label NIPLabel;

    @FXML
    private TextField namaGurujadwal;

    @FXML
    private TableView<Siswa> tableviewSiswa;

    @FXML
    private TableColumn<Siswa, String> nisSiswacolumndashboard;

    @FXML
    private TableColumn<Siswa, String> namaSiswacolumndashboard;

    @FXML
    private TableColumn<Siswa, String> kelasSiswacolumndashboard;

    @FXML
    private TableColumn<Siswa, String> statusSiswacolumndashboard;

    @FXML
    private TextField searchfieldSiswadashboard;

    @FXML
    private TableView<Guru> tableviewGuru;

    @FXML
    private TableColumn<Guru, String> nipGurucolumndashboard;

    @FXML
    private TableColumn<Guru, String> namaGurucolumndashboard;

    @FXML
    private TableColumn<Guru, String> statusGurucolumndashboard;

    @FXML
    private TextField searchfieldGurudashboard;

    // inisialisasi method
    // setiap method yang digunakan masukkan di sini
    public void initialize() {
        initializeTableSiswa();
        initializeTableGuru();
        initializeKalenderAkademik();
        JadwalController jadwalController = new JadwalController();
        jadwalController.initializeJadwalPelajaran();
        jadwalController.refreshJadwalTable();

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
                JadwalController jadwalcontroller = new JadwalController();
                jadwalcontroller.refreshJadwalTable();
                List<Siswa> siswaList = AppQuery.loadAllSiswaFromDatabase();
                tableviewSiswa.getItems().setAll(siswaList);
                List<Guru> guruList = AppQuery.loadAllGuruFromDatabase();
                tableviewGuru.getItems().setAll(guruList);
            } catch (SQLException el) {
                System.out.println("Failed to load acara from database: " + el.getMessage());

            }
        }
    }

    @FXML
    public void displayName(String namaGuru, int NIP) {
        nameLabel.setText("Selamat Datang, " + namaGuru + "!");
        nameLabel1.setText(namaGuru);
        NIPLabel.setText(String.valueOf(NIP));

    }

    public void handleLogoutButtonAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
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

    public void initializeTableSiswa() {
        nisSiswacolumndashboard.setCellValueFactory(new PropertyValueFactory<>("NIS"));
        namaSiswacolumndashboard.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        kelasSiswacolumndashboard.setCellValueFactory(new PropertyValueFactory<>("Kelas"));
        statusSiswacolumndashboard.setCellValueFactory(new PropertyValueFactory<>("Status"));

        loadAllSiswaToTable();

        autocompletetextFieldsiswa();
    }

    public void loadAllSiswaToTable() {
        try {
            List<Siswa> siswaList = AppQuery.loadAllSiswaFromDatabase();
            tableviewSiswa.getItems().setAll(siswaList);
        } catch (SQLException e) {
            System.out.println("Failed to load siswa from database: " + e.getMessage());
        }
    }

    public void autocompletetextFieldsiswa() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        searchfieldSiswadashboard.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> {
                tableviewSiswa.getItems().clear();
                try {
                    List<Siswa> siswaList;
                    if (newValue.isEmpty()) {
                        siswaList = AppQuery.loadAllSiswaFromDatabase();
                    } else {
                        siswaList = AppQuery.loadSiswaFromDatabase(newValue);
                    }
                    tableviewSiswa.getItems().setAll(siswaList);
                } catch (SQLException e) {
                    System.out.println("Failed to load siswa from database: " + e.getMessage());
                }
            });
            pause.playFromStart();
        });
    }

    public void initializeTableGuru() {
        nipGurucolumndashboard.setCellValueFactory(new PropertyValueFactory<>("NIP"));
        namaGurucolumndashboard.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        statusGurucolumndashboard.setCellValueFactory(new PropertyValueFactory<>("Status"));

        loadAllGuruToTable();

        autocompleteTextFieldGuru();
    }

    public void loadAllGuruToTable() {
        try {
            List<Guru> guruList = AppQuery.loadAllGuruFromDatabase();
            tableviewGuru.getItems().setAll(guruList);
        } catch (SQLException e) {
            System.out.println("Failed to load guru from database: " + e.getMessage());
        }
    }

    public void autocompleteTextFieldGuru() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        searchfieldGurudashboard.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> {
                tableviewGuru.getItems().clear();
                try {
                    List<Guru> guruList;
                    if (newValue.isEmpty()) {
                        guruList = AppQuery.loadAllGuruFromDatabase();
                    } else {
                        guruList = AppQuery.loadGuruFromDatabase(newValue);
                    }
                    tableviewGuru.getItems().setAll(guruList);
                } catch (SQLException e) {
                    System.out.println("Failed to load guru from database: " + e.getMessage());
                }
            });
            pause.playFromStart();
        });
    }

    // end of dashboard

    // Jadwal
    public class JadwalController {
        private Jadwal selectedJadwal = null;
        private TableView<Jadwal> selectedTableView = null;

        @FXML
        public void displayNamejadwal(String namaGuru, int NIP) {
            namaGurujadwal.setText(namaGuru);
            nipGurujadwal.setText(String.valueOf(NIP));

        }

        public void initializeJadwalPelajaran() {
            addItemsToComboBox(hariComboBox, Arrays.asList("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"));
            addItemsToComboBox(jadwalComboBox, Arrays.asList("Bahasa Indonesia", "Matematika", "Sejarah Islam"));
            addItemsToComboBox(kelasComboBox, Arrays.asList("1", "2", "3", "4", "5", "6"));

            pukulColumn.setTime(LocalTime.of(8, 0));

            configureTableColumn(seninJadwalTable, seninKelas, seninMapel, seninPukul);
            configureTableColumn(selasaJadwalTable, selasaKelas, selasaMapel, selasaPukul);
            configureTableColumn(rabuJadwalTable, rabuKelas, rabuMapel, rabuPukul);
            configureTableColumn(kamisJadwalTable, kamisKelas, kamisMapel, kamisPukul);
            configureTableColumn(jumatJadwalTable, jumatKelas, jumatMapel, jumatPukul);
            configureTableColumn(sabtuJadwalTable, sabtuKelas, sabtuMapel, sabtuPukul);

            TableView<?>[] tableViews = {
                    seninJadwalTableView,
                    selasaJadwalTabelView,
                    rabuJadwalTabelView,
                    kamisJadwalTabelView,
                    jumatJadwalTabelView,
                    sabtuJadwalTabelView
            };

            for (TableView<?> tableView : tableViews) {
                tableView.getSelectionModel().selectedItemProperty()
                        .addListener((obs, oldSelection, newSelection) -> {
                            if (newSelection != null) {
                                selectedJadwal = (Jadwal) newSelection;
                                selectedTableView = (TableView<Jadwal>) tableView;
                            }
                        });
            }

            addBtnJadwal.setOnAction(event -> addJadwal());

            deleteBtnJadwal.setOnAction(event -> deleteJadwal());

            clearBtnJadwal.setOnAction(event -> clearJadwal());

            updateBtnJadwal.setOnAction(event -> updateJadwal());
        }

        private void addJadwal() {
            String hari = hariComboBox.getValue();
            String jadwal = jadwalComboBox.getValue();
            String kelas = kelasComboBox.getValue();
            LocalTime pukul = pukulColumn.getTime();

            if (hari == null || hari.trim().isEmpty() ||
                    jadwal == null || jadwal.trim().isEmpty() ||
                    kelas == null || kelas.trim().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
                return;
            }

            String pukulString = pukul.format(DateTimeFormatter.ofPattern("HH:mm"));
            Jadwal jadwalPelajaran = new Jadwal(jadwal, pukulString, kelas, hari);

            try {
                AppQuery.insertJadwal(jadwalPelajaran);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            refreshJadwalTable();
        }

        private void deleteJadwal() {
            if (selectedJadwal != null && selectedTableView != null) {
                selectedTableView.getItems().remove(selectedJadwal);
                try {
                    AppQuery.deleteJadwal(selectedJadwal);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Schedule deleted successfully.");
                    alert.showAndWait();
                } catch (SQLException e) {
                    System.out.println("Failed to delete jadwal: " + e.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while deleting the schedule.");
                    alert.showAndWait();
                } finally {
                    selectedJadwal = null;
                    selectedTableView = null;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a schedule to delete.");
                alert.showAndWait();
            }
        }

        private void clearJadwal() {
            try {
                AppQuery.clearJadwal();
                refreshJadwalTable();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while clearing the schedule: " + e.getMessage());
                alert.showAndWait();
            }
        }

        private void updateJadwal() {
            if (selectedJadwal != null && selectedTableView != null) {
                String newHari = hariComboBox.getValue();
                String newJadwal = jadwalComboBox.getValue();
                String newKelas = kelasComboBox.getValue();
                LocalTime newPukul = pukulColumn.getTime();

                if (newHari == null || newHari.trim().isEmpty() ||
                        newJadwal == null || newJadwal.trim().isEmpty() ||
                        newKelas == null || newKelas.trim().isEmpty() ||
                        newPukul == null) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill in all fields.");
                    alert.showAndWait();
                    return;
                }

                String newPukulString = newPukul.format(DateTimeFormatter.ofPattern("HH:mm"));
                Jadwal newJadwalPelajaran = new Jadwal(newJadwal, newPukulString, newKelas, newHari);

                try {
                    AppQuery.updateJadwal(selectedJadwal, newJadwalPelajaran);
                    selectedTableView.getItems().set(selectedTableView.getItems().indexOf(selectedJadwal),
                            newJadwalPelajaran);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Schedule updated successfully.");
                    alert.showAndWait();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while updating the schedule.");
                    alert.showAndWait();
                } finally {
                    selectedJadwal = null;
                    selectedTableView = null;
                    refreshJadwalTable();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select a schedule to update.");
                alert.showAndWait();
            }
        }

        public void refreshJadwalTable() {
            ((TableView<Jadwal>) seninJadwalTable.getTableView()).getItems().clear();
            ((TableView<Jadwal>) selasaJadwalTable.getTableView()).getItems().clear();
            ((TableView<Jadwal>) rabuJadwalTable.getTableView()).getItems().clear();
            ((TableView<Jadwal>) kamisJadwalTable.getTableView()).getItems().clear();
            ((TableView<Jadwal>) jumatJadwalTable.getTableView()).getItems().clear();
            ((TableView<Jadwal>) sabtuJadwalTable.getTableView()).getItems().clear();

            List<Jadwal> jadwalList;
            try {
                jadwalList = AppQuery.loadJadwalFromDatabase();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            for (Jadwal jadwal : jadwalList) {
                switch (jadwal.getHari()) {
                    case "Senin":
                        ((TableView<Jadwal>) seninJadwalTable.getTableView()).getItems().add(jadwal);
                        break;
                    case "Selasa":
                        ((TableView<Jadwal>) selasaJadwalTable.getTableView()).getItems().add(jadwal);
                        break;
                    case "Rabu":
                        ((TableView<Jadwal>) rabuJadwalTable.getTableView()).getItems().add(jadwal);
                        break;
                    case "Kamis":
                        ((TableView<Jadwal>) kamisJadwalTable.getTableView()).getItems().add(jadwal);
                        break;
                    case "Jumat":
                        ((TableView<Jadwal>) jumatJadwalTable.getTableView()).getItems().add(jadwal);
                        break;
                    case "Sabtu":
                        ((TableView<Jadwal>) sabtuJadwalTable.getTableView()).getItems().add(jadwal);
                        break;
                }
            }
        }

        private void configureTableColumn(TableColumn<Jadwal, String> jadwalCol, TableColumn<Jadwal, String> kelasCol,
                TableColumn<Jadwal, String> mapelCol, TableColumn<Jadwal, String> pukulCol) {
            jadwalCol.setCellValueFactory(new PropertyValueFactory<>("hari"));
            kelasCol.setCellValueFactory(new PropertyValueFactory<>("kelas"));
            mapelCol.setCellValueFactory(new PropertyValueFactory<>("mapel"));
            pukulCol.setCellValueFactory(new PropertyValueFactory<>("pukul"));
        }

        private void addItemsToComboBox(ComboBox<String> comboBox, List<String> items) {
            for (String item : items) {
                comboBox.getItems().add(item);
            }
        }
    }

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

            if (keteranganAcara == null || keteranganAcara.trim().isEmpty() ||
                    semester == null || semester.trim().isEmpty() ||
                    tanggal == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields");

                alert.showAndWait();
                return;
            }

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
