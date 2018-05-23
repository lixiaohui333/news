package com.api.java;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {

	public static String getIp() {

		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return "http://"+address.getHostAddress();

	}

}