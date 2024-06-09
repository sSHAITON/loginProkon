package com.mycompany.loginprokon.model;

public class Nilai {
  private String nama;
  private String nis;
  private String kelas;
  private String semester;
  private String mataPelajaran;
  private int nilaiSiswa;
  private int kkmSiswa;
  private String ketSiswa;

  public Nilai(String nama, String nis, String kelas, String semester, String mataPelajaran, int nilaiSiswa,
      int kkmSiswa,
      String ketSiswa) {
    this.nama = nama;
    this.nis = nis;
    this.kelas = kelas;
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

  public String getKelas() {
    return kelas;
  }

  public void setKelas(String kelas) {
    this.kelas = kelas;
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

  public int getNilaiSiswa() {
    return nilaiSiswa;
  }

  public void setNilaiSiswa(int nilaiSiswa) {
    this.nilaiSiswa = nilaiSiswa;
  }

  public int getKkmSiswa() {
    return kkmSiswa;
  }

  public void setKkmSiswa(int kkmSiswa) {
    this.kkmSiswa = kkmSiswa;
  }

  public String getKetSiswa() {
    return ketSiswa;
  }

  public void setKetSiswa(String ketSiswa) {
    this.ketSiswa = ketSiswa;
  }
}