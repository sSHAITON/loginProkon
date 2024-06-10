package com.mycompany.loginprokon.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.mycompany.loginprokon.model.Acara;
import com.dlsc.gemsfx.daterange.DateRange;
import com.mycompany.loginprokon.data.DBConnection;
import com.mycompany.loginprokon.model.Jadwal;
import com.mycompany.loginprokon.model.Nilai;
import com.mycompany.loginprokon.model.NilaiHafalan;
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

  public static void updateAcara(Acara oldAcara, Acara newAcara) throws SQLException {
    String sql = "UPDATE kalenderisasi SET keterangan_acara = ?, semester = ?, tanggal_acara = ? WHERE keterangan_acara = ? AND semester = ? AND tanggal_acara = ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, newAcara.getKeteranganAcara());
      pstmt.setString(2, newAcara.getSemester());
      pstmt.setString(3, newAcara.getTanggalAsString());
      pstmt.setString(4, oldAcara.getKeteranganAcara());
      pstmt.setString(5, oldAcara.getSemester());
      pstmt.setString(6, oldAcara.getTanggalAsString());

      pstmt.executeUpdate();
    }
  }

  public static void clearAcara() throws SQLException {
    String sql = "DELETE FROM kalenderisasi";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.executeUpdate();

    }
  }

  public static void insertJadwal(Jadwal jadwal, Guru guru) throws SQLException {
    String sql = "INSERT INTO jadwal_pelajaran (mapel_jadwal, pukul_jadwal, kelas_jadwal, hari_jadwal, NIP) VALUES (?, ?, ?, ?, ?)";
    String selectSql = "SELECT admin.*, guru.Nama, guru.NIP FROM admin JOIN guru ON admin.NIP = guru.NIP";

    Connection con = DBConnection.getDBConn();
    String NIP = null;

    try {
      PreparedStatement prepare = con.prepareStatement(selectSql);
      ResultSet result = prepare.executeQuery();

      if (result.next()) {
        NIP = result.getString("NIP");
      }

      PreparedStatement pstmt = con.prepareStatement(sql);
      pstmt.setString(1, jadwal.getMapel());
      pstmt.setString(2, jadwal.getPukul());
      pstmt.setString(3, jadwal.getKelas());
      pstmt.setString(4, jadwal.getHari());
      pstmt.setString(5, NIP);

      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<Jadwal> loadJadwalFromDatabase(String NIP) throws SQLException {
    String sql = "SELECT mapel_jadwal, pukul_jadwal, kelas_jadwal, hari_jadwal FROM jadwal_pelajaran WHERE NIP = ?";
    List<Jadwal> jadwalList = new ArrayList<>();

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, NIP);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          String mapel = rs.getString("mapel_jadwal");
          String pukul = rs.getString("pukul_jadwal");
          String kelas = rs.getString("kelas_jadwal");
          String hari = rs.getString("hari_jadwal");

          Jadwal jadwal = new Jadwal(mapel, pukul, kelas, hari);
          jadwalList.add(jadwal);
        }
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
      pstmt.setString(1, newJadwal.getMapel());
      pstmt.setString(2, newJadwal.getPukul());
      pstmt.setString(3, newJadwal.getKelas());
      pstmt.setString(4, newJadwal.getHari());

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
    String sql = "SELECT nama, nis, kelas, semester, mata_pelajaran, nilai, kkm, ket "
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
        String kelas = rs.getString("kelas");
        String semester = rs.getString("semester");
        String mataPelajaran = rs.getString("mata_pelajaran");
        Integer nilaiSiswa = rs.getInt("nilai");
        Integer kkmSiswa = rs.getInt("kkm");
        String ketSiswa = rs.getString("ket");

        Nilai nilai = new Nilai(nama, nis, kelas, semester, mataPelajaran, nilaiSiswa, kkmSiswa, ketSiswa);
        nilaiList.add(nilai);
      }
    }
    return nilaiList;
  }

  public static void addNilai(Nilai nilai) throws SQLException {
    String sql = "INSERT INTO nilai_pelajaran (nama, nis, kelas, semester, mata_pelajaran, nilai, kkm, ket) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, nilai.getNama());
      pstmt.setString(2, nilai.getNis());
      pstmt.setString(3, nilai.getKelas());
      pstmt.setString(4, nilai.getSemester());
      pstmt.setString(5, nilai.getMataPelajaran());
      pstmt.setInt(6, nilai.getNilaiSiswa());
      pstmt.setInt(7, nilai.getKkmSiswa());
      pstmt.setString(8, nilai.getKetSiswa());
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
    String sql = "DELETE FROM nilai_pelajaran WHERE nama = ? AND nis = ? AND kelas = ? AND semester = ? AND mata_pelajaran = ? AND nilai = ? AND kkm = ? AND ket = ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, nilai.getNama());
      pstmt.setString(2, nilai.getNis());
      pstmt.setString(3, nilai.getKelas());
      pstmt.setString(4, nilai.getSemester());
      pstmt.setString(5, nilai.getMataPelajaran());
      pstmt.setInt(6, nilai.getNilaiSiswa());
      pstmt.setInt(7, nilai.getKkmSiswa());
      pstmt.setString(8, nilai.getKetSiswa());
      pstmt.executeUpdate();
    }
  }

  public static void upNilai(Nilai nilai) throws SQLException {
    String sql = "UPDATE nilai_pelajaran SET nilai = ?, kkm = ?, ket = ? WHERE nama = ? AND nis = ? AND semester = ? AND mata_pelajaran = ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setInt(1, nilai.getNilaiSiswa());
      pstmt.setInt(2, nilai.getKkmSiswa());
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
    String sql = "SELECT * FROM nilai_pelajaran WHERE nis LIKE ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      String searchParam = "%" + searchText + "%";
      pstmt.setString(1, searchParam);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          String nama = rs.getString("nama");
          String nis = rs.getString("nis");
          String kelas = rs.getString("kelas");
          String semester = rs.getString("semester");
          String mataPelajaran = rs.getString("mata_pelajaran");
          Integer nilai = rs.getInt("nilai");
          Integer kkm = rs.getInt("kkm");
          String ket = rs.getString("ket");

          Nilai nilaiObject = new Nilai(nama, nis, kelas, semester, mataPelajaran, nilai, kkm, ket);
          filteredNilaiList.add(nilaiObject);
        }
      }
    }
    return filteredNilaiList;
  }

  public static void insertNilai(NilaiHafalan nilaiHafalan) throws SQLException {

    String sql = "INSERT INTO nilai_hafalan_alquran (namaSiswa, nis, surat, tanggalhafalan, kelas, ayat) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement statement = DBConnection.getDBConn().prepareStatement(sql);
    statement.setString(1, nilaiHafalan.getNama());
    statement.setInt(2, nilaiHafalan.getNis());
    statement.setString(3, nilaiHafalan.getSurah());
    statement.setDate(4, nilaiHafalan.getTanggalAsDate());
    statement.setString(5, nilaiHafalan.getKelas());
    statement.setInt(6, nilaiHafalan.getBanyakayat());

    statement.executeUpdate();
  }

  public static List<NilaiHafalan> loadNilaiHafalanFromDatabase() throws SQLException {
    String sql = "SELECT namaSiswa, nis, surat, tanggalhafalan, kelas, ayat FROM nilai_hafalan_alquran";
    List<NilaiHafalan> nilaiList = new ArrayList<>();

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {

      while (rs.next()) {
        Date tanggalSql = rs.getDate("tanggalhafalan");
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(tanggalSql.getTime());
        LocalDate tanggal = sqlTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String nama = rs.getString("namaSiswa");
        Integer nis = rs.getInt("nis");
        String surah = rs.getString("surat");
        String kelas = rs.getString("kelas");
        Integer banyakayat = rs.getInt("ayat");

        NilaiHafalan nilaiHafalan = new NilaiHafalan(nama, nis, surah, tanggal, kelas, banyakayat);
        nilaiList.add(nilaiHafalan);
      }
    }

    return nilaiList;
  }

  public static void deleteNilai(NilaiHafalan nilai) throws SQLException {
    String sql = "DELETE FROM nilai_hafalan_alquran WHERE namaSiswa = ? AND nis = ? AND surat = ? AND tanggalhafalan = ? AND kelas = ? AND ayat = ? ";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql);) {
      pstmt.setString(1, nilai.getNama());
      pstmt.setInt(2, nilai.getNis());
      pstmt.setString(3, nilai.getSurah());
      pstmt.setDate(4, nilai.getTanggalAsDate());
      pstmt.setString(5, nilai.getKelas());
      pstmt.setInt(6, nilai.getBanyakayat());
      pstmt.executeUpdate();
    }

  }

  public static void updateNilai(NilaiHafalan oldNilai, NilaiHafalan newNilai) throws SQLException {
    String sql = "UPDATE nilai_hafalan_alquran SET namaSiswa = ?, nis = ?, surat = ?, tanggalhafalan = ?, kelas = ?, ayat = ? WHERE namaSiswa = ? AND nis = ? AND surat = ? AND tanggalhafalan = ? AND kelas = ? AND ayat = ?";

    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.setString(1, newNilai.getNama());
      pstmt.setInt(2, newNilai.getNis());
      pstmt.setString(3, newNilai.getSurah());
      pstmt.setDate(4, newNilai.getTanggalAsDate());
      pstmt.setString(5, newNilai.getKelas());
      pstmt.setInt(6, newNilai.getBanyakayat());

      pstmt.setString(7, oldNilai.getNama());
      pstmt.setInt(8, oldNilai.getNis());
      pstmt.setString(9, oldNilai.getSurah());
      pstmt.setDate(10, oldNilai.getTanggalAsDate());
      pstmt.setString(11, oldNilai.getKelas());
      pstmt.setInt(12, oldNilai.getBanyakayat());

      pstmt.executeUpdate();
    }
  }

  public static void clearNilai() throws SQLException {
    String sql = "DELETE FROM nilai_hafalan_alquran";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      pstmt.executeUpdate();
    }
  }

  public static List<NilaiHafalan> searchNilaiHafalan(String searchText) throws SQLException {
    List<NilaiHafalan> filteredNilaihafList = new ArrayList<>();
    String sql = "SELECT * FROM nilai_hafalan_alquran WHERE nis LIKE ?";
    try (PreparedStatement pstmt = DBConnection.getDBConn().prepareStatement(sql)) {
      String searchParam = "%" + searchText + "%";
      pstmt.setString(1, searchParam);

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Date tanggalSql = rs.getDate("tanggalhafalan");
          java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(tanggalSql.getTime());
          LocalDate tanggal = sqlTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          String nama = rs.getString("namaSiswa");
          Integer nis = rs.getInt("nis");
          String surah = rs.getString("surat");
          String kelas = rs.getString("kelas");
          Integer banyakayat = rs.getInt("ayat");

          NilaiHafalan nilaiObject = new NilaiHafalan(nama, nis, surah, tanggal, kelas, banyakayat);
          filteredNilaihafList.add(nilaiObject);
        }
      }
    }
    return filteredNilaihafList;
  }

}
