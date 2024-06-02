package com.mycompany.loginprokon.model;

public class Guru {
  private String NIP;
  private String Nama;
  private String Status;

  public Guru() {
  }

  public Guru(String NIP, String Nama, String Status) {
    this.NIP = NIP;
    this.Nama = Nama;
    this.Status = Status;
  }

  public String getNIP() {
    return NIP;
  }

  public void setNIP(String NIP) {
    this.NIP = NIP;
  }

  public String getNama() {
    return Nama;
  }

  public void setNama(String Nama) {
    this.Nama = Nama;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(String Status) {
    this.Status = Status;
  }
}