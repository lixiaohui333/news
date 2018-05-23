package com.api.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.api.util.GsonUtil;
import com.api.util.PropertiesUtil;

public class ApiAciton extends HttpServlet{
	 /**@Fields serialVersionUID : TODO*/ 
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		String prifex = "/api/";
		String key = uri.substring(uri.indexOf(prifex)+prifex.length(), uri.length());
		
		System.out.println("uri:"+uri);
		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String tempkey = headerNames.nextElement();
			System.out.println(tempkey+":"+ req.getHeader(tempkey));
		}
		
		s2obj(key, req, resp);
		
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void s2obj(String key,HttpServletRequest req, HttpServletResponse resp){
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = resp.getWriter();
			PropertiesUtil.getLastChangeLoad();
			String classname = PropertiesUtil.getValue(key);
			if(null != classname){
				Class clazz = Class.forName(classname);
				Object obj = clazz.newInstance();
				out.print(GsonUtil.toJson(obj));
			}else{
				resp.sendError(400, "not config this class");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
