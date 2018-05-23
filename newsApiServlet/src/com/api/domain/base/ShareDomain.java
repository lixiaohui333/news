package com.api.domain.base;

import java.io.Serializable;

public class ShareDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	public String title = "术康"; // 标题

	public String desc = "详情"; // 描述
	public String url="http://www.postop.cn/index.html"; // 跳转地址
	public String picUrl="http://www.postop.cn/style/images/logo.png"; // 图片

	public ShareDomain() {

	}

	public ShareDomain(String title, String desc, String url, String picUrl) {
		super();
		this.title = title;
		this.desc = desc;
		this.url = url;
		this.picUrl = picUrl;
	}
	
	


}
