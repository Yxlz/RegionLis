package com.rlis.inspection.model.barcode;

public class OrdItem {
  // 项目代码 LIS 代码-1380730625
  private String arcimCode;
  // 医嘱ID
  private String ordId;
  // 费用标示，传N
  private String urgencyFlag = "N";

  private String regState;
  
  private String sampleHospID;//送检机构

  public String getArcimCode() {
    return arcimCode;
  }

  public void setArcimCode(String arcimCode) {
    this.arcimCode = arcimCode;
  }

  public String getOrdId() {
    return ordId;
  }

  public void setOrdId(String ordId) {
    this.ordId = ordId;
  }

  public String getUrgencyFlag() {
    return urgencyFlag;
  }

  public void setUrgencyFlag(String urgencyFlag) {
    this.urgencyFlag = urgencyFlag;
  }

  public String getRegState() {
    return regState;
  }

  public void setRegState(String regState) {
    this.regState = regState;
  }

public String getSampleHospID() {
	return sampleHospID;
}

public void setSampleHospID(String sampleHospID) {
	this.sampleHospID = sampleHospID;
}



}
