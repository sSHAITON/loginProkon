package com.mycompany.loginprokon.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.mycompany.loginprokon.model.Acara;
import com.dlsc.gemsfx.daterange.DateRange;
import com.mycompany.loginprokon.data.DBConnection;
import com.mycompany.loginprokon.model.Jadwal;
import com.mycompany.loginprokon.model.Nilai;
import com.mycompany.loginprokon.model.Siswa;
import com.mycompany.loginprokon.model.Guru;

public class AppQuery {

  public static void insertAcara(Acara acara) throws SQLException {
    String sql = "INSERT INTO kalenderisasi (keterangan_acara, semester, tanggal_acara) VALUES (?, ?, ?)";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, acara.getKeteranganAcara());
      pstmt.setString(2, acara.getSemester());
      pstmt.setString(3, acara.getTanggalAsString());

      pstmt.executeUpdate();
    }
  }

  public static List<Acara> loadAcaraFromDatabase() throws SQLException {
    String sql = "SELECT keterangan_acara, semester, tanggal_acara FROM kalenderisasi";
    List<Acara> acaraList = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        String keteranganAcara = rs.getString("keterangan_acara");
        String semester = rs.getString("semester");
        String tanggalAsString = rs.getString("tanggal_acara");

        String[] parts = tanggalAsString.split(" to ");
        if (parts.length < 2) {
          throw new IllegalArgumentException(
              "Invalid tanggalAsString format. Expected 'MMM d, yyyy to MMM d, yyyy', got '" + tanggalAsString + "'");
        }
        LocalDate startDate = LocalDate.parse(parts[0], formatter);
        LocalDate endDate = LocalDate.parse(parts[1], formatter);
        DateRange tanggal = new DateRange(startDate, endDate);

        Acara acara = new Acara(keteranganAcara, semester, tanggal);
        acaraList.add(acara);
      }
    }
    return acaraList;
  }

  public static void deleteAcara(Acara acara) throws SQLException {
    String sql = "DELETE FROM kalenderisasi WHERE keterangan_acara = ? AND semester = ? AND tanggal_acara = ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, acara.getKeteranganAcara());
      pstmt.setString(2, acara.getSemester());
      pstmt.setString(3, acara.getTanggalAsString());

      pstmt.executeUpdate();
    }
  }

  public static void insertJadwal(Jadwal jadwal) throws SQLException {
    String sql = "INSERT INTO jadwal_pelajaran (mapel_jadwal, pukul_jadwal, kelas_jadwal, hari_jadwal) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, jadwal.getMapel());
      pstmt.setString(2, jadwal.getPukul());
      pstmt.setString(3, jadwal.getKelas());
      pstmt.setString(4, jadwal.getHari());

      pstmt.executeUpdate();
    }
  }

  public static List<Jadwal> loadJadwalFromDatabase() throws SQLException {
    String sql = "SELECT mapel_jadwal, pukul_jadwal, kelas_jadwal, hari_jadwal FROM jadwal_pelajaran";
    List<Jadwal> jadwalList = new ArrayList<>();

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        String mapel = rs.getString("mapel_jadwal");
        String pukul = rs.getString("pukul_jadwal");
        String kelas = rs.getString("kelas_jadwal");
        String hari = rs.getString("hari_jadwal");

        Jadwal jadwal = new Jadwal(mapel, pukul, kelas, hari);
        jadwalList.add(jadwal);
      }
    }

    return jadwalList;
  }

  public static void deleteJadwal(Jadwal jadwal) throws SQLException {
    String sql = "DELETE FROM jadwal_pelajaran WHERE mapel_jadwal = ? AND pukul_jadwal = ? AND kelas_jadwal = ? AND hari_jadwal = ?";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, jadwal.getMapel());
      pstmt.setString(2, jadwal.getPukul());
      pstmt.setString(3, jadwal.getKelas());
      pstmt.setString(4, jadwal.getHari());

      pstmt.executeUpdate();
    }
  }

  public static void clearJadwal() throws SQLException {
    String sql = "DELETE FROM jadwal_pelajaran";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      int affectedRows = pstmt.executeUpdate();
      System.out.println("Deleted " + affectedRows + " nilai records.");
      pstmt.executeUpdate();
    }
  }

  public static void updateJadwal(Jadwal oldJadwal, Jadwal newJadwal) throws SQLException {
    String sql = "UPDATE jadwal_pelajaran SET mapel_jadwal = ?, pukul_jadwal = ?, kelas_jadwal = ?, hari_jadwal = ? " +
        "WHERE mapel_jadwal = ? AND pukul_jadwal = ? AND kelas_jadwal = ? AND hari_jadwal = ?";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      // Set new values
      pstmt.setString(1, newJadwal.getMapel());
      pstmt.setString(2, newJadwal.getPukul());
      pstmt.setString(3, newJadwal.getKelas());
      pstmt.setString(4, newJadwal.getHari());

      // Set old values
      pstmt.setString(5, oldJadwal.getMapel());
      pstmt.setString(6, oldJadwal.getPukul());
      pstmt.setString(7, oldJadwal.getKelas());
      pstmt.setString(8, oldJadwal.getHari());

      pstmt.executeUpdate();
    }
  }

  public static List<Siswa> loadSiswaFromDatabase(String query) throws SQLException {
    List<Siswa> matchingSiswa = new ArrayList<>();
    String sql = "SELECT * FROM siswa WHERE NIS LIKE ?";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, query + "%");

      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        int NIS = rs.getInt("NIS");
        String Nama = rs.getString("Nama");
        String Kelas = rs.getString("Kelas");
        String Status = rs.getString("Status");

        Siswa siswa = new Siswa(NIS, Nama, Kelas, Status);
        matchingSiswa.add(siswa);
      }
    }

    return matchingSiswa;
  }

  public static List<Siswa> loadAllSiswaFromDatabase() throws SQLException {
    List<Siswa> allSiswa = new ArrayList<>();
    String sql = "SELECT NIS, Nama, Kelas, Status FROM siswa";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        int NIS = rs.getInt("NIS");
        String Nama = rs.getString("Nama");
        String Kelas = rs.getString("Kelas");
        String Status = rs.getString("Status");

        Siswa siswa = new Siswa(NIS, Nama, Kelas, Status);
        allSiswa.add(siswa);
      }
    }

    return allSiswa;
  }

  public static List<Guru> loadGuruFromDatabase(String query) throws SQLException {
    List<Guru> matchingGuru = new ArrayList<>();
    String sql = "SELECT NIP, Nama, Status FROM guru WHERE NIP LIKE ?";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, query + "%");

      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        String NIP = rs.getString("NIP");
        String Nama = rs.getString("Nama");
        String Status = rs.getString("Status");

        Guru guru = new Guru(NIP, Nama, Status);
        matchingGuru.add(guru);
      }
    }

    return matchingGuru;
  }

  public static List<Guru> loadAllGuruFromDatabase() throws SQLException {
    List<Guru> allGuru = new ArrayList<>();
    String sql = "SELECT NIP, Nama, Status FROM guru";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        String NIP = rs.getString("NIP");
        String Nama = rs.getString("Nama");
        String Status = rs.getString("Status");

        Guru guru = new Guru(NIP, Nama, Status);
        allGuru.add(guru);
      }
    }

    return allGuru;
  }

  public static List<Nilai> loadNilaiFromDatabase(String filterCondition) throws SQLException {
    String sql = "SELECT nama, nis, semester, mata_pelajaran, nilai, kkm, ket "
        + "FROM nilai_pelajaran ";
    if (filterCondition != null && !filterCondition.isEmpty()) {
      sql += "WHERE " + filterCondition;
    }

    List<Nilai> nilaiList = new ArrayList<>();

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        String nama = rs.getString("nama");
        String nis = rs.getString("nis");
        String semester = rs.getString("semester");
        String mataPelajaran = rs.getString("mata_pelajaran");
        String nilaiSiswa = rs.getString("nilai");
        String kkmSiswa = rs.getString("kkm");
        String ketSiswa = rs.getString("ket");

        Nilai nilai = new Nilai(nama, nis, semester, mataPelajaran, nilaiSiswa, kkmSiswa, ketSiswa);
        nilaiList.add(nilai);
      }
    }
    return nilaiList;
  }

  public static void addNilai(Nilai nilai) throws SQLException {
    String sql = "INSERT INTO nilai_pelajaran (nama, nis, semester, mata_pelajaran, nilai, kkm, ket) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, nilai.getNama());
      pstmt.setString(2, nilai.getNis());
      pstmt.setString(3, nilai.getSemester());
      pstmt.setString(4, nilai.getMataPelajaran());
      pstmt.setString(5, nilai.getNilaiSiswa());
      pstmt.setString(6, nilai.getKkmSiswa());
      pstmt.setString(7, nilai.getKetSiswa());
      pstmt.executeUpdate();
    }
  }

  public static void clrNilai() throws SQLException {
    String sql = "DELETE FROM nilai_pelajaran";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      int affectedRows = pstmt.executeUpdate();
      System.out.println("Deleted " + affectedRows + " nilai records.");
    }
  }

  public static void dltNilai(Nilai nilai) throws SQLException {
    String sql = "DELETE FROM nilai_pelajaran WHERE nama = ? AND nis = ? AND semester = ? AND mata_pelajaran = ? AND nilai = ? AND kkm = ? AND ket = ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, nilai.getNama());
      pstmt.setString(2, nilai.getNis());
      pstmt.setString(3, nilai.getSemester());
      pstmt.setString(4, nilai.getMataPelajaran());
      pstmt.setString(5, nilai.getNilaiSiswa());
      pstmt.setString(6, nilai.getKkmSiswa());
      pstmt.setString(7, nilai.getKetSiswa());
      pstmt.executeUpdate();
    }
  }

  public static void upNilai(Nilai nilai) throws SQLException {
    String sql = "UPDATE nilai_pelajaran SET nilai = ?, kkm = ?, ket = ? WHERE nama = ? AND nis = ? AND semester = ? AND mata_pelajaran = ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, nilai.getNilaiSiswa());
      pstmt.setString(2, nilai.getKkmSiswa());
      pstmt.setString(3, nilai.getKetSiswa());
      pstmt.setString(4, nilai.getNama());
      pstmt.setString(5, nilai.getNis());
      pstmt.setString(6, nilai.getSemester());
      pstmt.setString(7, nilai.getMataPelajaran());
      pstmt.executeUpdate();
    }
  }

  public static List<Nilai> searchNilai(String searchText) throws SQLException {
    List<Nilai> filteredNilaiList = new ArrayList<>();
    String sql = "SELECT * FROM nilai_pelajaran WHERE nama LIKE ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      String searchParam = "%" + searchText + "%";
      pstmt.setString(1, searchParam);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          String nama = rs.getString("nama");
          String nis = rs.getString("nis");
          String semester = rs.getString("semester");
          String mataPelajaran = rs.getString("mata_pelajaran");
          String nilai = rs.getString("nilai");
          String kkm = rs.getString("kkm");
          String ket = rs.getString("ket");

          Nilai nilaiObject = new Nilai(nama, nis, semester, mataPelajaran, nilai, kkm, ket);
          filteredNilaiList.add(nilaiObject);
        }
      }
    }
    return filteredNilaiList;
  }

}
