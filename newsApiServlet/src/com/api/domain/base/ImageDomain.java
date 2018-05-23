package com.api.domain.base;

import java.io.Serializable;

public class ImageDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	public String src;
	
	public ActionDomain action;

	public ImageDomain() {

	}

	public ImageDomain(String src, ActionDomain action) {
		this.src = src;
		this.action = action;
	}

	


	
	
}
