package com.api.java;


public class MD5Utils {

	public static void main(String[] args) {

		long currentTimeMillis = System.currentTimeMillis();
		
		String md5 = MD5Tool.getMD5("D:\\DOWNLOAD\\蚂蜂窝峨眉山.pdf");
		System.out.println((System.currentTimeMillis()-currentTimeMillis)+" "+md5);
	}

}