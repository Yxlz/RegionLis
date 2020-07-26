package com.rlis.inspection.model.barcode;

import java.util.List;

public class Episode {

  private String episodeID;
  // 别人类别 6外院
  private String admType = "6";
  // 出生时间
  private String admDate;

  private String admTime;
  // 病人科室 医院名称
  private String admLocCode;
  // 病区 不传
  private String wardCode;
  // 床号
  private String bed;
  // 医生编码 不传
  private String doctorCode;

  private List<OrdItem> orders;

  private List<Diagnosis> diagnosis;

  public String getEpisodeID() {
    return episodeID;
  }

  public void setEpisodeID(String episodeID) {
    this.episodeID = episodeID;
  }

  public String getAdmType() {
    return admType;
  }

  public void setAdmType(String admType) {
    this.admType = admType;
  }

  public String getAdmDate() {
    return admDate;
  }

  public void setAdmDate(String admDate) {
    this.admDate = admDate;
  }

  public String getAdmTime() {
    return admTime;
  }

  public void setAdmTime(String admTime) {
    this.admTime = admTime;
  }

  public String getAdmLocCode() {
    return admLocCode;
  }

  public void setAdmLocCode(String admLocCode) {
    this.admLocCode = admLocCode;
  }

  public String getWardCode() {
    return wardCode;
  }

  public void setWardCode(String wardCode) {
    this.wardCode = wardCode;
  }

  public String getBed() {
    return bed;
  }

  public void setBed(String bed) {
    this.bed = bed;
  }

  public String getDoctorCode() {
    return doctorCode;
  }

  public void setDoctorCode(String doctorCode) {
    this.doctorCode = doctorCode;
  }

  public List<OrdItem> getOrders() {
    return orders;
  }

  public void setOrders(List<OrdItem> orders) {
    this.orders = orders;
  }

  public List<Diagnosis> getDiagnosis() {
    return diagnosis;
  }

  public void setDiagnosis(List<Diagnosis> diagnosis) {
    this.diagnosis = diagnosis;
  }



}
