package com.rlis.common.exception.barcode;

/**
 * jackson dataformatter exception
 * 
 * @author lixying
 * @date 2017年4月21日 下午4:37:53
 * @since 1.0.0
 */
public class Bean2XmlFailureException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -4951431300950400945L;

  /**
   * 
   */

  public Bean2XmlFailureException(Object bean, Throwable cause) {
    super("将bean[" + bean + "]转换成对应的xml失败", cause);
  }

}
