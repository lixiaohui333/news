package com.api.java;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class JAVATest {

	public static void main(String[] args) {

		int aaa = 00000100;

		// System.out.println(GsonUtil.toJson(new DeviceInfoDomain()));
		InetAddress address=null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String aa = address.getHostAddress();
		System.out.println(MD5Tool.getMD5("111111"));

	}

	public static class Stu {

	}

}