package com.api.main;

import java.io.FileWriter;
import java.io.IOException;

public class CreateDo {

	public static String doPath = "";

	public static void main(String[] args) {

		String basePath = doPath = Thread.currentThread()
				.getContextClassLoader().getResource(".").getPath();
		basePath = basePath.substring(1).replace("classes/", "");

		doPath = basePath.replace("WEB-INF/", "");
//		createMyDo("api.main", GsonUtil.toJson(new HttpResultStartDomain()));
		

	}

	private static void createMyDo(String fileName, String json) {
		

		FileWriter fw = null;
		try {
			fw = new FileWriter(doPath + fileName);
			fw.write(json);
			fw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
