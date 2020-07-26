package com.rlis.inspection.model.barcode;

/**
 * 向杏和申请条码的请求bean
 * 
 * @author lixying
 * @date 2017年4月21日 上午11:11:10
 * @since 1.0.0
 */
public class Request {
	// 病人号
	private String patNo;
	// 病人唯一号
	private String rowId;
	// 病人姓名
	private String patName;
	// 性别
	private String sex;
	// 年龄
	// private String age;
	// 生日
	private String birthDay;
	// DOB
	private String DOB;
	// 卡号或者身份证号
	private String id;
	// 电话
	private String telOffice;
	// 地址
	private String address;
	// 工作单位
	private String workUnit;

	private EpisodeInfo episodeInfo;

	public String getPatNo() {
		return patNo;
	}

	public void setPatNo(String patNo) {
		this.patNo = patNo;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/*
	 * public String getAge() { return age; }
	 * 
	 * public void setAge(String age) { this.age = age; }
	 */
	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelOffice() {
		return telOffice;
	}

	public void setTelOffice(String telOffice) {
		this.telOffice = telOffice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public EpisodeInfo getEpisodeInfo() {
		return episodeInfo;
	}

	public void setEpisodeInfo(EpisodeInfo episodeInfo) {
		this.episodeInfo = episodeInfo;
	}

}
