package com.api.domain.http.base;

import java.io.Serializable;

public class HttpResultDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	public int sysStatus=0;
	public int apiStatus=0;
	public String info="success!";
	public long timestamp=System.currentTimeMillis();
	

}
