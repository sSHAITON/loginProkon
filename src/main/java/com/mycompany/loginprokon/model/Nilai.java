package com.mycompany.loginprokon.model;

public class Nilai {
  private String nama;
  private String nis;
  private String semester;
  private String mataPelajaran;
  private String nilaiSiswa;
  private String kkmSiswa;
  private String ketSiswa;

  public Nilai(String nama, String nis, String semester, String mataPelajaran, String nilaiSiswa, String kkmSiswa,
      String ketSiswa) {
    this.nama = nama;
    this.nis = nis;
    this.semester = semester;
    this.mataPelajaran = mataPelajaran;
    this.nilaiSiswa = nilaiSiswa;
    this.kkmSiswa = kkmSiswa;
    this.ketSiswa = ketSiswa;
  }

  // Getters and Setters

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getNis() {
    return nis;
  }

  public void setNis(String nis) {
    this.nis = nis;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public String getMataPelajaran() {
    return mataPelajaran;
  }

  public void setMataPelajaran(String mataPelajaran) {
    this.mataPelajaran = mataPelajaran;
  }

  public String getNilaiSiswa() {
    return nilaiSiswa;
  }

  public void setNilaiSiswa(String nilaiSiswa) {
    this.nilaiSiswa = nilaiSiswa;
  }

  public String getKkmSiswa() {
    return kkmSiswa;
  }

  public void setKkmSiswa(String kkmSiswa) {
    this.kkmSiswa = kkmSiswa;
  }

  public String getKetSiswa() {
    return ketSiswa;
  }

  public void setKetSiswa(String ketSiswa) {
    this.ketSiswa = ketSiswa;
  }
}