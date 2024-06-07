package com.mycompany.loginprokon.model;

import java.time.LocalDate;
import java.sql.Date;

public class NilaiHafalan {

  private String nama;
  private Integer nis;
  private String surah;
  private LocalDate tanggal;
  private String kelas;
  private Integer banyakayat;

  // Constructor
  public NilaiHafalan(String nama, Integer nis, String surah, LocalDate tanggal, String kelas, Integer banyakayat) {

    this.nama = nama;
    this.nis = nis;
    this.surah = surah;
    this.tanggal = tanggal;
    this.kelas = kelas;
    this.banyakayat = banyakayat;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public Integer getNis() {
    return nis;
  }

  public void setNis(Integer nis) {
    this.nis = nis;
  }

  public String getSurah() {
    return surah;
  }

  public void setSurah(String surah) {
    this.surah = surah;
  }

  public LocalDate getTanggal() {
    return tanggal;
  }

  public void setTanggal(LocalDate tanggal) {

    this.tanggal = tanggal;
  }

  public Date getTanggalAsDate() {
    return Date.valueOf(tanggal);
  }

  public String getKelas() {
    return kelas;
  }

  public void setKelas(String kelas) {
    this.kelas = kelas;
  }

  public Integer getBanyakayat() {
    return banyakayat;
  }

  public void setBanyakayat(Integer banyakayat) {
    this.banyakayat = banyakayat;
  }

}
