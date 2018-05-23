package com.api.domain.base;

import java.io.Serializable;

public class ActionDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	// 统一标识id
	public String rel;
	// 请求的具体url
	public String href;

	public String text;

	public String method;
	
	public String params;
	

	public ActionDomain() {

	}

	public ActionDomain(String rel, String href, String method) {
		this.rel = rel;
		this.href = href;
		this.method = method;
	}
	
	public ActionDomain(String rel, String href, String method,String text) {
		this.rel = rel;
		this.href = href;
		this.method = method;
		this.text = text;
	}
	
	public ActionDomain(String rel, String href, String method,String text,String params) {
		this.rel = rel;
		this.href = href;
		this.method = method;
		this.text = text;
		this.params = params;
	}


	
	
}
