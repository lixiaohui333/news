package com.api.domain.http.interview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.api.domain.http.base.HttpResultDomain;

public class HttpResultFrameDomain extends HttpResultDomain {

	private static final long serialVersionUID = 1L;
	public DataDomain data = new DataDomain();;
	
	
	class DataDomain {
		
		public List<Menu> menuList=new ArrayList<>();
		{
			menuList.add(new Menu("JAVA基础", "java.base", "http://www.raincent.com/uploadfile/2016/0106/20160106094639786.jpg"));
			menuList.add(new Menu("JAVA框架", "java.expert", "http://www.raincent.com/uploadfile/2016/0106/20160106094639786.jpg"));
			menuList.add(new Menu("Android基础", "android.base", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=862704645,1557247143&fm=27&gp=0.jpg"));
			menuList.add(new Menu("Android框架", "android.expert", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=862704645,1557247143&fm=27&gp=0.jpg"));
			
			menuList.add(new Menu("自定义题库", "other.custom", ""));
			menuList.add(new Menu("备忘录", "other.memo", ""));
			
			menuList.add(new Menu("阿里巴巴Android开发手册", "android.ali", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=862704645,1557247143&fm=27&gp=0.jpg"));
			menuList.add(new Menu("阿里巴巴Java开发手册", "java.ali", "http://www.raincent.com/uploadfile/2016/0106/20160106094639786.jpg"));

		}
		
		class Menu{
			
			public String name;
			
			public String rel;
			
			public String icon;

			public Menu(String name, String rel, String icon) {
				this.name = name;
				this.rel = rel;
				this.icon = icon;
			}
			
			
			
			
		}
	}
	
	
}
