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
          System.out.println("Deleted " + affectedRows + " nilai records.");pstmt.executeUpdate();
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
}
