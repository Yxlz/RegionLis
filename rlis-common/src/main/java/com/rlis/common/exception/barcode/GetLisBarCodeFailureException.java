package com.rlis.common.exception.barcode;
/**
 * 杏和lis接口返回失败代码时的异常
 * @author lixying
 * @date 2017年4月21日 下午4:27:13
 * @since 1.0.0
 */
public class GetLisBarCodeFailureException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -3595454616376758840L;

  public GetLisBarCodeFailureException(String resultContent) {
    super(resultContent);
  }

}
