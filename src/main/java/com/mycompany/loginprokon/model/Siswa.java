package com.mycompany.loginprokon.model;

public class Siswa {
  private int NIS;
  private String Nama;
  private String Kelas;
  private String Status;

  // Constructor
  public Siswa(int NIS, String Nama, String Kelas, String Status) {
    this.NIS = NIS;
    this.Nama = Nama;
    this.Kelas = Kelas;
    this.Status = Status;
  }

  // Getters and Setters
  public int getNIS() {
    return NIS;
  }

  public void setNIS(int NIS) {
    this.NIS = NIS;
  }

  public String getNama() {
    return Nama;
  }

  public void setNama(String Nama) {
    this.Nama = Nama;
  }

  public String getKelas() {
    return Kelas;
  }

  public void setKelas(String Kelas) {
    this.Kelas = Kelas;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(String Status) {
    this.Status = Status;
  }
}