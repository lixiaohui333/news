package com.api.domain.http.interview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.api.domain.http.base.HttpResultDomain;

public class HttpResultInterviewListDomain extends HttpResultDomain {

	private static final long serialVersionUID = 1L;
	public DataDomain data = new DataDomain();;
	
	
	class DataDomain {
		
		public List<Menu> list=new ArrayList<>();
		{
			list.add(new Menu("Java面向对象编程"));
			list.add(new Menu("接口的意义"));
			list.add(new Menu("抽象类的意义"));
			list.add(new Menu("多态的好处;代码中如何实现多态"));
			list.add(new Menu("线程和进程有什么区别?"));
			list.add(new Menu("什么是线程?"));
			list.add(new Menu("外观模式"));
			list.add(new Menu("代理模式模式"));

		}
		
		class Menu{
			
			public String id;
			
			public String title;
			

			public Menu(String title) {
				this.id = "1";
				this.title = title;
			}
			
			
			
		}
	}
	
	
}
