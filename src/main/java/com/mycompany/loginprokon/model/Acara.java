package com.mycompany.loginprokon.model;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import com.dlsc.gemsfx.daterange.DateRange;

public class Acara {
  private String keteranganAcara;
  private String semester;
  private DateRange tanggal;

  public Acara(String keteranganAcara, String semester, DateRange tanggal) {
    this.keteranganAcara = keteranganAcara;
    this.semester = semester;
    this.tanggal = tanggal;
  }

  public String getKeteranganAcara() {
    return keteranganAcara;
  }

  public void setKeteranganAcara(String keteranganAcara) {
    this.keteranganAcara = keteranganAcara;
  }

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
  }

  public DateRange getTanggal() {
    return tanggal;
  }

  public void setTanggal(DateRange tanggal) {
    this.tanggal = tanggal;
  }

  public String getTanggalAsString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
    return tanggal.getStartDate().format(formatter) + " to " + tanggal.getEndDate().format(formatter);
  }
}
