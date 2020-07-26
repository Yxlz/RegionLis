package com.rlis.inspection.model.barcode;

import java.util.List;

/**
 * 杏和条码号接口返回信息
 * 
 * @author lixying
 * @date 2017年4月21日 下午3:26:47
 * @since 1.0.0
 */
public class Response {

  // 成功
  private static final String SUCCESS = "0";

  // 响应码
  private String resultCode;
  // 响应信息
  private String errorMsg;

  private List<String> specimenCode;

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }


  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public List<String> getSpecimenCode() {
    return specimenCode;
  }

  public void setSpecimenCode(List<String> specimenCode) {
    this.specimenCode = specimenCode;
  }

  public boolean isSuccess() {
    return SUCCESS.equals(this.resultCode);
  }



}
