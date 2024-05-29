package com.mycompany.loginprokon.model;

import javafx.beans.property.SimpleStringProperty;

public class Jadwal {
  private final SimpleStringProperty mapel;
  private final SimpleStringProperty pukul;
  private final SimpleStringProperty kelas;
  private final SimpleStringProperty hari;

  public Jadwal(String mapel, String pukul, String kelas, String hari) {
    this.mapel = new SimpleStringProperty(mapel);
    this.pukul = new SimpleStringProperty(pukul);
    this.kelas = new SimpleStringProperty(kelas);
    this.hari = new SimpleStringProperty(hari);
  }

  public String getMapel() {
    return mapel.get();
  }

  public void setMapel(String mapel) {
    this.mapel.set(mapel);
  }

  public String getPukul() {
    return pukul.get();
  }

  public void setPukul(String pukul) {
    this.pukul.set(pukul);
  }

  public String getKelas() {
    return kelas.get();
  }

  public void setKelas(String kelas) {
    this.kelas.set(kelas);
  }

  public String getHari() {
    return hari.get();
  }

  public void setHari(String hari) {
    this.hari.set(hari);
  }
}