package com.api.java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class CreatePage {

	public static void main(String[] args) {
		String xmlPath = "";
		String pagePath = "";
		String basePath = xmlPath = Thread.currentThread()
				.getContextClassLoader().getResource(".").getPath();
		basePath = basePath.substring(1).replace("classes/", "");
		xmlPath = basePath + "web.xml";
		pagePath = basePath.replace("WEB-INF/", "") + "index.html";
		System.out.println(basePath);
		try {
		//反射获取com.api.api下所有类信息
		Map<String, Object> map = GetClassUtils.getClassData();
		//所有全类名
		List<String> names  = (List<String>) map.get("names");
		//所有类名
		List<String> simpleNames  = (List<String>) map.get("simpleNames");
		//所有类描述
		List<String> descriptions  = (List<String>) map.get("descriptions");
		//所有URLparttern
		List<String> urlPatterns  = (List<String>) map.get("urlPatterns");
		//生成web.xml
		createXml(names, simpleNames,urlPatterns, xmlPath);
		//生成index.html
//		createHtmlFile(urlPatterns, descriptions, pagePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	* @author hezhoujun
	* @Title: createHtmlFile 
	* @Description: 生成页面方法
	* @param urlPatterns
	* @param descriptions
	* @param pagePath
	* @throws IOException void
	*/
	public static void createHtmlFile(List<String> urlPatterns,List<String> descriptions, String pagePath)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE HTML><html lang=\"en\"><head>\r\n");
		sb.append("<meta charset=\"UTF-8\">\r\n");
		sb.append("<title>API Menu</title>\r\n");
		sb.append("</head>\r\n");
		sb.append("<body>\r\n");
		sb.append("<p>\r\n");
		sb.append("<h3>列表</H3>\r\n");
		sb.append("<p></p>\r\n");
		sb.append("<ul>\r\n");
		FileWriter fw = null;
		try {
			fw = new FileWriter(pagePath);
			for(int i = 0;i < urlPatterns.size();i++){
				sb.append("<li>"+descriptions.get(i)+"<br/>\r\n");
				sb.append("<a target=\"_blank\" href=\"" + urlPatterns.get(i) + "\">" + urlPatterns.get(i)
						+ "</a></li><br/>\r\n");
			}
			sb.append("</ul>\r\n");
			sb.append("</body></html>\r\n");
			System.out.println(sb.toString());
			fw.write(sb.toString());
			fw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}
	
	public static void createXml(List<String> names,List<String> simpleNames,List<String> urlPatterns,String xmlPath){
		SAXReader reader = new SAXReader(); 
		   Document doc;
		try {
			String path = xmlPath.substring(0,xmlPath.lastIndexOf("/"));
			doc = reader.read(path+"/web1.xml");
			Element rootElement = doc.getRootElement(); 
			for(int i = 0;i < names.size();i++){
				Element sevletElement = rootElement.addElement("servlet");
				Element servlet_name = sevletElement.addElement("servlet-name");
				Element servlet_class = sevletElement.addElement("servlet-class");
				servlet_name.setText(simpleNames.get(i));
				servlet_class.setText(names.get(i));
				Element servlet_mapping = rootElement.addElement("servlet-mapping");
				Element servlet_name02 = servlet_mapping.addElement("servlet-name");
				Element url_pattern = servlet_mapping.addElement("url-pattern");
				servlet_name02.setText(simpleNames.get(i));
				url_pattern.setText(urlPatterns.get(i));
			}
			// 创建文件输出的时候，自动缩进的格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileWriter(path+"/web.xml"), format);
			writer.write(doc);
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
