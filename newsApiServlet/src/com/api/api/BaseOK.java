package com.api.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeaderServlet
 */
public class BaseOK extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();

		// //返回登录界面
//		pw.println("<html>");
//		pw.println("<html>");
//		pw.println("<body>");

		String method = request.getMethod();
//		pw.println("<font color='red'>Method:" + method + "</font><br>");
//
//		pw.println("<br>");
//		pw.println("<br>");
//
//		pw.println("<font color='red'>Header: </font><br>");
		
//		 if (ServletFileUpload.isMultipartContent(request)){
//	            //创建ServletFileUpload实例
//	            ServletFileUpload fileUpload = new ServletFileUpload();
//	            try {
//	                //解析request请求 返回FileItemStream的iterator实例
//	                FileItemIterator iter = fileUpload.getItemIterator(request);
//	                InputStream is = null;//输出流
//	                //迭代取出
//	                while (iter.hasNext()){
//	                    FileItemStream item = iter.next();//获取文件流
//	                    String name = item.getFieldName();//返回表单中标签的name值
//	                    is = item.openStream();//得到对应表单的输出流
//	                    if (item.isFormField()){//如果是非文件域,设置进入map,这里要注意多值处理
//	                        setFormParam(name,is);//如果不是文件上传,处理
//	                    }else {
//	                        if (is.available()>0){//如果输出流的内容大于0
//	                            String fname = item.getName();//获取文件名
//	                            Streams.copy(is,new FileOutputStream(PATH+fname),true);//拷贝内容到上传路径
//	                            params.put(name,new String[]{fname});//把文件名设置进request中
//	                        }
//	 
//	                    }
//	                }
//	 
//	            } catch (FileUploadException e) {
//	                e.printStackTrace();
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        }else {
//	            params = request.getParameterMap();//如果不是文件上传请求,则直接设置map
//	        }
		
		

		Enumeration<String> headerNames = request.getHeaderNames();

		System.out.println("Method:" + method);
		System.out.println("");

		while (headerNames.hasMoreElements()) {
			String nextElement = headerNames.nextElement();
//			pw.println(nextElement + ":" + request.getHeader(nextElement));
//
//			pw.println("<br>");

			System.out.println(nextElement + ":"
					+ request.getHeader(nextElement));

		}
//		pw.println("<br>");
//		pw.println("<br>");

		System.out.println("");

//		pw.println("<font color='red'>parameter: </font><br>");
		

		BufferedReader br = request.getReader();

		String valueString = null;
		while ((valueString = br.readLine()) != null) {
			System.out.println(valueString);
		}
		br.close();

		System.out.println("");
		SimpleDateFormat sdf_simple_name = new SimpleDateFormat(
				"yyyyMMdd HH:mm:ss");

		System.out.println(""
				+ sdf_simple_name.format(new Date(System.currentTimeMillis())));

		System.out
				.println("------------------------------------------------------");

//		pw.print("<a target='_blank' href='/baseOK'>js.html</a></li><br/>");
//
//		pw.println("</body>");
//
//		pw.println("</html>");
//		pw.println("{\"data\":\"sadjasdj\",\"errMsg\":\"test\",\"resultCode\":1}");
//		try {
//			Thread.sleep(20*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		pw.println("{\"Data\":[{\"area\":\"华北\",\"area_id\":\"100000\"}],\"Message\":\"xxxx\",\"Status\":1}");
		
		pw.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public String getUrlPattern() {
		return "/base.ok";
	}
}
