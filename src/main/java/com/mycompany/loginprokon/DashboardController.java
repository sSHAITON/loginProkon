package com.mycompany.loginprokon;

import com.mycompany.loginprokon.model.Acara;
import com.mycompany.loginprokon.model.Jadwal;
import com.mycompany.loginprokon.model.Nilai;
import com.mycompany.loginprokon.model.NilaiHafalan;
import com.mycompany.loginprokon.model.Siswa;
import com.mycompany.loginprokon.model.Guru;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.dlsc.gemsfx.TimePicker;
import com.dlsc.gemsfx.daterange.DateRange;
import com.dlsc.gemsfx.daterange.DateRangePicker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mycompany.loginprokon.data.AppQuery;
import com.mycompany.loginprokon.data.DBConnection;
import javafx.collections.ObservableList;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import com.itextpdf.text.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
    private AnchorPane grafHaf;

    @FXML
    private LineChart<String, Number> grafikHafalan;

    @FXML
    private Button grafikHafBtn;

    @FXML
    private Button backBtnHaf;

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
    private Button pdfBtnKalender;

    @FXML
    private TableView<NilaiHafalan> TableHafalan;

    @FXML
    private TextField namaSurat;

    @FXML
    private TextField BanyakAyat;

    @FXML
    private DatePicker tanggalHafalan;

    @FXML
    private TextField namaHafalan;

    @FXML
    private TextField nisHafalan;

    @FXML
    private TextField kelasHafalan;

    @FXML
    private Button addBtnHafalan;

    @FXML
    private Button delBtnHafalan;

    @FXML
    private TableColumn<NilaiHafalan, String> suratColumn;

    @FXML
    private TableColumn<NilaiHafalan, Integer> banyakAyatColumn;

    @FXML
    private TableColumn<NilaiHafalan, DatePicker> tanggalColumn;

    @FXML
    private TextField searchHafalan;

    @FXML
    private Button pdfBtnHafalan;

    // inisialisasi method
    // setiap method yang digunakan masukkan di sini
    public void initialize() {
        initializeTableSiswa();
        initializeTableGuru();
        initializeKalenderAkademik();
        initializeNilai();
        initializeNilaiHafalan();
        initializeHafalanChart();
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
        grafHaf.setVisible(false);

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
            } else if (event.getSource() == monitoringHafBtn || event.getSource() == backBtnHaf) {
                monitoringHaf.setVisible(true);
                monitoringHafBtn.getStyleClass().add("navbtn-selected");
            } else if (event.getSource() == daftarguruBtn) {
                daftarguru.setVisible(true);
            } else if (event.getSource() == daftarsiswBtn) {
                daftarsiswa.setVisible(true);
            } else if (event.getSource() == grafikHafBtn) {
                grafHaf.setVisible(true);
                monitoringHafBtn.getStyleClass().add("navbtn-selected");
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
                List<Nilai> nilaiList = AppQuery.loadNilaiFromDatabase("");
                tableViewNilai.getItems().setAll(nilaiList);
                List<NilaiHafalan> nilaiHafalanList = AppQuery.loadNilaiHafalanFromDatabase();
                TableHafalan.getItems().setAll(nilaiHafalanList);
                initializeHafalanChart();
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

            clearBtnJadwal.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to clear the schedule?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    clearJadwal();
                }
            });

            updateBtnJadwal.setOnAction(event -> updateJadwal());
            pdfBtnJadwal.setOnAction(event -> {
                try {
                    createJadwalPdf(AppQuery.loadJadwalFromDatabase(NIPLabel.getText()));
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            });
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
                AppQuery.insertJadwal(jadwalPelajaran, null);
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
                jadwalList = AppQuery.loadJadwalFromDatabase(NIPLabel.getText());
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

        public void createJadwalPdf(List<Jadwal> jadwalList) {
            try {
                Document document = new Document(PageSize.LEGAL);
                PdfWriter.getInstance(document, new FileOutputStream("pdf/Jadwal.pdf"));
                document.open();

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new int[] { 1, 4 });

                Image logo = Image.getInstance(
                        "C:\\Users\\Hannya\\Documents\\NetBeansProjects\\loginProkon\\src\\main\\resources\\com\\mycompany\\loginprokon\\img\\icon.png");
                logo.scaleToFit(100, 100);
                PdfPCell imageCell = new PdfPCell(logo);
                imageCell.setBorder(PdfPCell.NO_BORDER);
                table.addCell(imageCell);

                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
                Paragraph title = new Paragraph("Sekolah Tahfidzul Qur'an Baitul Muttaqin", fontTitle);
                title.setAlignment(Element.ALIGN_LEFT);

                Paragraph address = new Paragraph(
                        "Jl. Antapani Lama No. 32 , Bandung, Jawa Barat \n" +
                                "Telepon: +62 813-8744-6436\n" +
                                "Email: pkbmbaitulmuttaqin@gmail.com");
                address.setAlignment(Element.ALIGN_LEFT);

                PdfPCell textCell = new PdfPCell();
                textCell.addElement(title);
                textCell.addElement(address);
                textCell.setBorder(PdfPCell.NO_BORDER);
                table.addCell(textCell);

                document.add(table);

                LineSeparator separator = new LineSeparator();
                separator.setOffset(-2);
                document.add(separator);

                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
                Paragraph judul = new Paragraph("JADWAL PELAJARAN", titleFont);
                judul.setAlignment(Element.ALIGN_CENTER);
                judul.setSpacingBefore(5);
                judul.setSpacingAfter(5);
                document.add(judul);

                String[] days = { "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu" };

                for (String day : days) {
                    List<Jadwal> dayJadwalList = jadwalList.stream()
                            .filter(jadwal -> jadwal.getHari().equalsIgnoreCase(day))
                            .collect(Collectors.toList());

                    if (!dayJadwalList.isEmpty()) {
                        Paragraph paragraph = new Paragraph(day, new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
                        paragraph.setAlignment(Element.ALIGN_CENTER);
                        document.add(paragraph);

                        document.add(new Paragraph("\n"));

                        PdfPTable dayTable = new PdfPTable(4);
                        PdfPCell cell;

                        String[] headers = { "Hari", "Kelas", "Mapel", "Pukul" };
                        for (String header : headers) {
                            cell = new PdfPCell(new Phrase(header));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            dayTable.addCell(cell);
                        }

                        for (Jadwal jadwal : dayJadwalList) {
                            dayTable.addCell(jadwal.getHari());
                            dayTable.addCell(jadwal.getKelas());
                            dayTable.addCell(jadwal.getMapel());
                            dayTable.addCell(jadwal.getPukul().toString());
                        }

                        document.add(dayTable);
                        document.add(Chunk.NEWLINE);
                    }
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PDF Creation");
                alert.setHeaderText(null);
                alert.setContentText("PDF created successfully.");
                alert.showAndWait();

                document.close();
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("PDF Creation Failed");
                alert.setContentText("Failed to create PDF: " + e.getMessage());
                alert.showAndWait();
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

        pdfBtnKalender.setOnAction(event -> createPDFkalender());
    }

    public void createPDFkalender() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdf/kalender_akademik.pdf"));
            document.open();

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[] { 1, 4 });

            Image logo = Image.getInstance(
                    "/C:\\Users\\Hannya\\Documents\\NetBeansProjects\\loginProkon\\src\\main\\resources\\com\\mycompany\\loginprokon\\img\\icon.png");
            logo.scaleToFit(100, 100);
            PdfPCell imageCell = new PdfPCell(logo);
            imageCell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(imageCell);

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Sekolah Tahfidzul Qur'an Baitul Muttaqin", fontTitle);
            title.setAlignment(Element.ALIGN_LEFT);

            Paragraph address = new Paragraph(
                    "Jl. Antapani Lama No. 32 , Bandung, Jawa Barat \n" +
                            "Telepon: +62 813-8744-6436\n" +
                            "Email: pkbmbaitulmuttaqin@gmail.com");
            address.setAlignment(Element.ALIGN_LEFT);

            PdfPCell textCell = new PdfPCell();
            textCell.addElement(title);
            textCell.addElement(address);
            textCell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(textCell);

            document.add(table);

            LineSeparator separator = new LineSeparator();
            separator.setOffset(-2);
            document.add(separator);

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph judul = new Paragraph("KALENDERISASI AKADEMIK", titleFont);
            judul.setAlignment(Element.ALIGN_CENTER);
            judul.setSpacingBefore(10);
            judul.setSpacingAfter(10);
            document.add(judul);

            PdfPTable table1 = new PdfPTable(3);
            table1.setWidthPercentage(100);
            table1.setSpacingBefore(10f);
            table1.setSpacingAfter(10f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Keterangan Acara"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Semester"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Tanggal"));

            table1.addCell(cell1);
            table1.addCell(cell2);
            table1.addCell(cell3);

            ObservableList<Acara> kalenderList = tableViewKalender.getItems();
            for (Acara kalender : kalenderList) {
                table1.addCell(kalender.getKeteranganAcara());
                table1.addCell(kalender.getSemester());
                table1.addCell(kalender.getTanggalAsString());
            }

            document.add(table1);
            document.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF Creation");
            alert.setHeaderText(null);
            alert.setContentText("PDF created successfully.");
            alert.showAndWait();

            System.out.println("PDF created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nilai Insertion Failed");
            alert.setContentText("Failed to add nilai: " + e.getMessage());
            alert.showAndWait();
        }
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

        try {
            List<Nilai> nilaiList = AppQuery.loadNilaiFromDatabase("");
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
                tableViewNilai.getItems().add(nilai);
            } catch (SQLException e) {
                System.out.println("Failed to insert nilai: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Nilai Insertion Failed");
                alert.setContentText("Failed to add nilai: " + e.getMessage());
                alert.showAndWait();
            }
        });

        clrBtnNilai.setOnAction(event -> {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Penghapusan");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Apakah anda yakin akan menghapus semua database?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    AppQuery.clrNilai();
                    tableViewNilai.getItems().clear();
                } catch (SQLException e) {
                    System.out.println("Failed to delete all nilai: " + e.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Nilai Deletion Failed");
                    alert.setContentText("Failed to delete all nilai: " + e.getMessage());
                    alert.showAndWait();
                }
            } else {
                // do nothing
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
                // selectedNilai.setKkmSiswa(kkmNilai.getText());
                // selectedNilai.setKetSiswa(ketNilai.getText());

                try {
                    AppQuery.upNilai(selectedNilai);
                    tableViewNilai.refresh();
                } catch (SQLException e) {
                    System.out.println("Failed to update nilai: " + e.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setHeaderText("Baris Tidak Dipilih");
                alert.setContentText("Pilih baris untuk memperbarui nilai.");
                alert.showAndWait();
            }
        });

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                List<Nilai> filteredNilaiList = AppQuery.searchNilai(newValue);
                tableViewNilai.getItems().setAll(filteredNilaiList);

                pdfBtnNilai.setDisable(newValue.trim().isEmpty());
            } catch (SQLException e) {
                System.out.println("Failed to search nilai: " + e.getMessage());
            }
        });

        pdfBtnNilai.setDisable(searchField.getText().trim().isEmpty());

        pdfBtnNilai.setOnAction(event -> createPDFnilai());
    }

    public void createPDFnilai() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "pdf/nilai_" + timeStamp + ".pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new int[] { 1, 4 });

            Image logo = Image.getInstance(
                    "C:\\Users\\Hannya\\Documents\\NetBeansProjects\\loginProkon\\src\\main\\resources\\com\\mycompany\\loginprokon\\img\\icon.png");
            logo.scaleToFit(100, 100);
            PdfPCell imageCell = new PdfPCell(logo);
            imageCell.setBorder(PdfPCell.NO_BORDER);
            headerTable.addCell(imageCell);

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Sekolah Tahfidzul Qur'an Baitul Muttaqin", fontTitle);
            title.setAlignment(Element.ALIGN_LEFT);

            Paragraph address = new Paragraph(
                    "Jl. Antapani Lama No. 32 , Bandung, Jawa Barat \n" +
                            "Telepon: +62 813-8744-6436\n" +
                            "Email: pkbmbaitulmuttaqin@gmail.com");
            address.setAlignment(Element.ALIGN_LEFT);

            PdfPCell textCell = new PdfPCell();
            textCell.addElement(title);
            textCell.addElement(address);
            textCell.setBorder(PdfPCell.NO_BORDER);
            headerTable.addCell(textCell);

            document.add(headerTable);

            LineSeparator separator = new LineSeparator();
            separator.setOffset(-2);
            document.add(new Chunk(separator));

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph judul = new Paragraph("Laporan Nilai Siswa", titleFont);
            judul.setAlignment(Element.ALIGN_CENTER);
            judul.setSpacingBefore(10);
            judul.setSpacingAfter(10);
            document.add(judul);

            if (!tableViewNilai.getItems().isEmpty()) {
                Nilai firstNilai = tableViewNilai.getItems().get(0);

                Font dataDiriFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
                Paragraph dataDiriTitle = new Paragraph("DATA DIRI", dataDiriFont);
                dataDiriTitle.setSpacingBefore(10);
                dataDiriTitle.setSpacingAfter(5);
                document.add(dataDiriTitle);

                Paragraph dataDiri = new Paragraph(
                        "Nama: " + firstNilai.getNama() + "\n" +
                                "NIS: " + firstNilai.getNis() + "\n" +
                                "Semester: " + firstNilai.getSemester());
                dataDiri.setSpacingAfter(10);
                document.add(dataDiri);
            }

            PdfPTable nilaiTable = new PdfPTable(4);
            nilaiTable.setWidthPercentage(100);
            nilaiTable.setSpacingBefore(10f);
            nilaiTable.setSpacingAfter(10f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Mata Pelajaran"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Nilai"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("KKM"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Keterangan"));

            nilaiTable.addCell(cell1);
            nilaiTable.addCell(cell2);
            nilaiTable.addCell(cell3);
            nilaiTable.addCell(cell4);

            for (Nilai nilai : tableViewNilai.getItems()) {
                nilaiTable.addCell(nilai.getMataPelajaran());
                nilaiTable.addCell(nilai.getNilaiSiswa());
                nilaiTable.addCell(nilai.getKkmSiswa());
                nilaiTable.addCell(nilai.getKetSiswa());
            }

            document.add(nilaiTable);
            document.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF Creation");
            alert.setHeaderText(null);
            alert.setContentText("PDF created successfully.");
            alert.showAndWait();

            System.out.println("PDF Created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("PDF Creation Failed");
            alert.setContentText("Failed to create PDF: " + e.getMessage());
            alert.showAndWait();
        }
    }

    // end of nilai rapot

    // Monitoring Hafalan
    public void initializeNilaiHafalan() {
        suratColumn.setCellValueFactory(new PropertyValueFactory<>("surah"));
        banyakAyatColumn.setCellValueFactory(new PropertyValueFactory<>("banyakayat"));
        tanggalColumn.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        try {
            List<NilaiHafalan> nilaiHaf = AppQuery.loadNilaiHafalanFromDatabase();
            TableHafalan.getItems().setAll(nilaiHaf);
        } catch (SQLException e) {
            System.out.println("Failed to load nilai from database: " + e.getMessage());
        }

        addBtnHafalan.setOnAction(event -> {
            String nama = namaHafalan.getText();
            Integer nis = Integer.valueOf(BanyakAyat.getText());
            String surah = namaSurat.getText();
            String kelas = kelasHafalan.getText();
            Integer banyakayat = Integer.valueOf(BanyakAyat.getText());
            LocalDate tanggal = tanggalHafalan.getValue();

            if (nama == null || nama.trim().isEmpty() ||
                    surah == null || surah.trim().isEmpty() ||
                    kelas == null || kelas.trim().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields");
                alert.showAndWait();
            } else {
                NilaiHafalan nilai = new NilaiHafalan(nama, nis, surah, tanggal, kelas, banyakayat);
                try {
                    AppQuery.insertNilai(nilai);
                    TableHafalan.getItems().add(nilai);
                } catch (SQLException e) {
                    System.out.println("Failed to insert nilai: " + e.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Nilai Insertion Failed");
                    alert.setContentText("Failed to add nilai: " + e.getMessage());
                    alert.showAndWait();
                }
            }
        });

        delBtnHafalan.setOnAction(event -> {
            NilaiHafalan selectedNilaiHafalan = TableHafalan.getSelectionModel().getSelectedItem();
            if (selectedNilaiHafalan != null) {
                try {
                    AppQuery.deleteNilai(selectedNilaiHafalan);
                    TableHafalan.getItems().remove(selectedNilaiHafalan);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Data berhasil dihapus");
                    alert.showAndWait();
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Gagal menghapus data: " + e.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a record to delete");
                alert.showAndWait();
            }
        });

        searchHafalan.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                List<NilaiHafalan> filteredNilaihafList = AppQuery.searchNilaiHafalan(newValue);
                TableHafalan.getItems().setAll(filteredNilaihafList);

                pdfBtnNilai.setDisable(newValue.trim().isEmpty());
                initializeHafalanChart();
            } catch (SQLException e) {
                System.out.println("Failed to search nilai: " + e.getMessage());
            }
        });

        pdfBtnHafalan.setOnAction(event -> createPDFHaf());
    }

    public void createPDFHaf() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("pdf/nilai_hafalan.pdf"));
            document.open();

            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new int[] { 1, 4 });

            Image logo = Image.getInstance(
                    "C:\\Users\\Hannya\\Documents\\NetBeansProjects\\loginProkon\\src\\main\\resources\\com\\mycompany\\loginprokon\\img\\icon.png");
            logo.scaleToFit(100, 100);
            PdfPCell imageCell = new PdfPCell(logo);
            imageCell.setBorder(PdfPCell.NO_BORDER);
            headerTable.addCell(imageCell);

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Sekolah Tahfidzul Qur'an Baitul Muttaqin", fontTitle);
            title.setAlignment(Element.ALIGN_LEFT);

            Paragraph address = new Paragraph(
                    "Jl. Antapani Lama No. 32 , Bandung, Jawa Barat \n" +
                            "Telepon: +62 813-8744-6436\n" +
                            "Email: pkbmbaitulmuttaqin@gmail,com");
            address.setAlignment(Element.ALIGN_LEFT);

            PdfPCell textCell = new PdfPCell();
            textCell.addElement(title);
            textCell.addElement(address);
            textCell.setBorder(PdfPCell.NO_BORDER);
            headerTable.addCell(textCell);

            document.add(headerTable);

            LineSeparator separator = new LineSeparator();
            separator.setOffset(-2);
            document.add(new Chunk(separator));

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph judul = new Paragraph("Laporan Nilai Hafalan Siswa", titleFont);
            judul.setAlignment(Element.ALIGN_CENTER);
            judul.setSpacingBefore(10);
            judul.setSpacingAfter(10);
            document.add(judul);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            table.addCell("Surah");
            table.addCell("Banyak Ayat");
            table.addCell("Tanggal");

            for (NilaiHafalan nilai : TableHafalan.getItems()) {
                table.addCell(nilai.getSurah());
                table.addCell(String.valueOf(nilai.getBanyakayat()));
                table.addCell(nilai.getTanggal().toString());
            }

            document.add(table);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("PDF Creation Failed");
            alert.setContentText("Failed to create PDF: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void initializeHafalanChart() {
        try {
            List<NilaiHafalan> nilaiHafalanList = AppQuery.loadNilaiHafalanFromDatabase();

            XYChart.Series<String, Number> series = new XYChart.Series<>();

            for (NilaiHafalan nilaiHafalan : nilaiHafalanList) {
                series.getData()
                        .add(new XYChart.Data<>(nilaiHafalan.getTanggalAsDate().toString(),
                                nilaiHafalan.getBanyakayat()));
            }

            grafikHafalan.getData().clear();
            grafikHafalan.getData().add(series);
        } catch (SQLException e) {
            System.out.println("Failed to load nilai hafalan from database: " + e.getMessage());
        }
    }
    // end of monitoring hafalan
}
