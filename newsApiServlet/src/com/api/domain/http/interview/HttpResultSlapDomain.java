package com.api.domain.http.interview;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import com.api.domain.http.base.HttpResultDomain;

public class HttpResultSlapDomain extends HttpResultDomain {

	private static final long serialVersionUID = 1L;
	public DataDomain data = new DataDomain();;
	
	
	class DataDomain {
		
		
		public DataDomain() {
			String[] adUrls = {
					"http://pic1.win4000.com/wallpaper/3/57a2f791b627e.jpg",
					"http://pic1.win4000.com/wallpaper/b/57fee673ef6c2.jpg",
					"http://pic1.win4000.com/wallpaper/2017-12-02/5a22658520a08.jpg",
					"http://pic1.win4000.com/wallpaper/c/53a8de7b0c14e.jpg",
			};
			int index = new Random().nextInt(adUrls.length);
			adUrl=adUrls[index];
		}
		
		public int version=1;
		
		public String adUrl;
		
		public int splashDuration
		= 300;
		
	}
	
	
}
