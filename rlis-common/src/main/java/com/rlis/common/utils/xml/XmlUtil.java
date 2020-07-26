package com.rlis.common.utils.xml;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rlis.common.exception.barcode.Bean2XmlFailureException;
import com.rlis.common.exception.barcode.Xml2BeanFailureException;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * jackson dataformatter xml
 * 
 * @author lixying
 * @date 2017年4月21日 上午11:28:44
 * @since 1.0.0
 */
public class XmlUtil {
  /**
   * 将javabean 转换成xml字符串
   * 
   * @param bean
   * @param capitalize 首字母大写
   * @return
   */
  public static String convertBeanToXml(Object bean, boolean capitalize) {
    XmlMapper xmlMapper = new XmlMapper();
    try {
      String xml = xmlMapper.writeValueAsString(bean);
      if (capitalize) {
        return xmlStrFirstSpelling(xml, true);
      }
      return xml;
    } catch (JsonProcessingException e) {
      throw new Bean2XmlFailureException(bean, e);
    }
  }

  /**
   * 转换xml字符串节点的首字母大小写
   * 
   * @param xml
   * @param capitalize 是否大写
   * @return
   */
  public static String xmlStrFirstSpelling(String xml, boolean capitalize) {
    Set<String> ns = new HashSet<String>();
    Pattern ptn = Pattern.compile("<(/?)(.+?)(/?)>");
    Matcher mer = ptn.matcher(xml);
    while (mer.find()) {
      ns.add(mer.group(2));
    }
    for (String n : ns) {
      xml = xml.replaceAll("\\b" + n + "\\b",
          capitalize ? StringUtils.capitalize(n) : StringUtils.uncapitalize(n));// 全字替换
    }
    return xml;
  }

  /**
   * 将 xml 转换成对应的 bean
   * 
   * @param clazz
   * @param xml
   * @return
   */
  public static <T> T convertXmlToBean(Class<T> clazz, String xml)  {
 // 只能识别首字母为小写的节点名称
    xml = xmlStrFirstSpelling(xml, false);

    XmlMapper xmlMapper = new XmlMapper();
    try {
      return xmlMapper.readValue(xml, clazz);
    } catch (Exception e) {
      throw new Xml2BeanFailureException(xml, e);
    }
  }
  
  	/**
  	 * 截取XML字符串中的pid
  	 * @param xml
  	 * @return
  	 */
	public static String splitPidFromXml(String xml) {
		Pattern p = Pattern.compile("<Pid>(\\w+)</Pid>");
		Matcher m = p.matcher(xml);
		String subStr = "";
		while (m.find()) {
			subStr = m.group(1);
		}
		return subStr;
	}

  /***************************************************************************************************************
  public static void main(String[] args) {
    new XmlUtil().test2();
  }

  public void test1() {
    Request r = new Request();
    EpisodeInfo ei = new EpisodeInfo();
    Episode e = new Episode();

    List<Diagnosis> dl = new ArrayList<Diagnosis>();
    Diagnosis d = new Diagnosis();
    d.setDiagDesc("上感");
    dl.add(d);
    e.setDiagnosis(dl);

    List<OrdItem> os = new ArrayList<>();
    os.add(new OrdItem());
    
    e.setOrders(os);

    ei.setEpisode(e);
    r.setEpisodeInfo(ei);
    String str = convertBeanToXml(r, true);

    System.out.println(str);

  }

  public void test2() {
    String xml =
        "<Response><ResultCode/><ResultContent/><BarCodes><BarCode>aaaaaaaaaaaaaaa</BarCode><BarCode>bbbbbbbbbbbbbbb</BarCode></BarCodes></Response>";
    Response r;
	try {
		r = convertXmlToBean(Response.class, "sdadasdasda");
	} catch (WstxUnexpectedCharException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  //  System.out.println(r);

  }
  
  public void test3(){
    Response r = new Response();
    
    List<String> bs = new ArrayList<>();
    bs.add(("aaa"));
    bs.add(("bbb"));
    
  //  r.setBarCodes(bs);
    
    System.out.println(convertBeanToXml(r, true));
  }
  
 // ****************************************************************************************************/
  


}
