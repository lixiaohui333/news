package com.api.java;

import java.io.Serializable;

import com.api.domain.base.ShareDomain;
import com.api.util.GsonUtil;

public class JsonUtils {

	public static void main(String[] args) {

		ActionDomain t = new ActionDomain();
		t.rel = "html.js.tel";

		String json = GsonUtil.toJson(t);
		System.out.println(json);
	}

}

class ActionDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	// 统一标识id
	public String rel;

	public DataDomain data = new DataDomain();

	public ActionDomain() {

	}

	class DataDomain {
		
		public String mobile="15828380434";
		
		public String date="9:00-12:00";
		
		public String name="同仁堂大药店";
		

	}

}