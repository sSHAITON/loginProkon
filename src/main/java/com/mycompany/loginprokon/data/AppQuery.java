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
import com.mycompany.loginprokon.model.Nilai;

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
  
    public static List<Nilai> loadNilaiFromDatabase(String filterCondition) throws SQLException {
        String sql = "SELECT nama, nis, semester, mata_pelajaran, nilai, kkm, ket "
                + "FROM nilai_pelajaran ";
        if (filterCondition != null && !filterCondition.isEmpty()) {
            sql += "WHERE " + filterCondition;
        }

        List<Nilai> nilaiList = new ArrayList<>();

        try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

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
