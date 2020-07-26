package com.rlis.inspection.domain;

public class RlInspecRequisitionBarcode {
    private String id;

    private String requisitionId;

    private String barcode;

    private String patientId;

    private String patientName;

    private String patientType;

    private String patientSex;

    private String patientCode;

    private String patientAge;

    private String items;

    private String excuteSection;

    private String reportPlace;

    private String reportTime;

    private String sampleCollectDate;

    private String sampleCollectAdvice;

    private String sampleState;

    private String printTime;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRequisitionId() {
        return requisitionId;
    }

    public void setRequisitionId(String requisitionId) {
        this.requisitionId = requisitionId == null ? null : requisitionId.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType == null ? null : patientType.trim();
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode == null ? null : patientCode.trim();
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge == null ? null : patientAge.trim();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
    }

    public String getExcuteSection() {
        return excuteSection;
    }

    public void setExcuteSection(String excuteSection) {
        this.excuteSection = excuteSection == null ? null : excuteSection.trim();
    }

    public String getReportPlace() {
        return reportPlace;
    }

    public void setReportPlace(String reportPlace) {
        this.reportPlace = reportPlace == null ? null : reportPlace.trim();
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public String getSampleCollectDate() {
        return sampleCollectDate;
    }

    public void setSampleCollectDate(String sampleCollectDate) {
        this.sampleCollectDate = sampleCollectDate == null ? null : sampleCollectDate.trim();
    }

    public String getSampleCollectAdvice() {
        return sampleCollectAdvice;
    }

    public void setSampleCollectAdvice(String sampleCollectAdvice) {
        this.sampleCollectAdvice = sampleCollectAdvice == null ? null : sampleCollectAdvice.trim();
    }

    public String getSampleState() {
        return sampleState;
    }

    public void setSampleState(String sampleState) {
        this.sampleState = sampleState == null ? null : sampleState.trim();
    }

    public String getPrintTime() {
        return printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime == null ? null : printTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}