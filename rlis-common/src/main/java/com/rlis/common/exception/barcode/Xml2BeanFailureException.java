package com.rlis.common.exception.barcode;

/**
 * jackson dataformatter exception
 * 
 * @author lixying
 * @date 2017年4月21日 下午4:37:53
 * @since 1.0.0
 */
public class Xml2BeanFailureException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 8164595658183617477L;

  public Xml2BeanFailureException(String xml, Throwable cause) {
    super("将xml[" + xml + "]转换成对应的bean失败", cause);
  }

}
