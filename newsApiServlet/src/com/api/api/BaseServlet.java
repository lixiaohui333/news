package com.api.api;

import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet{
	 /**@Fields serialVersionUID : TODO*/ 
	private static final long serialVersionUID = 1L;

	/** 
	* @author hezhoujun
	* @Title: getDescription 
	* @Description: 获取备注
	* @return String
	*/
	public abstract String getDescription();
	
	/** 
	* @author hezhoujun
	* @Title: getUrlPattern 
	* @Description: 获取url-pattern的值
	* @return String
	*/
	public abstract String getUrlPattern();
}
