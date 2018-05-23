package com.api.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.api.util.redis.RedisCache;

public class PropertiesUtil {
	private static String MSG_TEMPLATE_MODIFY_TIME = "MAP_JSON_TEMPLATE_MODIFY_TIME";
	private static Map<String,String> map = new HashMap<String,String>();
	static{
		initProperties();
	}
	
	/** 
	* @author hezhoujun
	* @Title: initProperties 
	* @Description: 初始化短信模版配置文件
	*/
	private static void initProperties(){
		Properties properties = new Properties();
		try{
			properties.load(PropertiesUtil.class.getResourceAsStream("/resources/mapperjson.properties"));
			Set<Entry<Object, Object>> entries =  properties.entrySet();
			Iterator<Entry<Object, Object>> iterator = entries.iterator();
			while(iterator.hasNext()){
				Entry<Object, Object> entry = iterator.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if(null != key && value != null
						&& StringUtils.isNotBlank(key.toString()) 
						&& StringUtils.isNotBlank(value.toString())){
						map.put(key.toString(), value.toString());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 
	* @author hezhoujun
	* @Title: getValue 
	* @Description: 根据key获取对应配置的value
	* @param key
	* @return String
	*/
	public static String getValue(String key){
		String value = map.get(key);
		return value;
	}
	
	public static Map<String,String> getPropertieMap(){
		return map;
	}
	
	/** 
	* @author hezhoujun
	* @Title: getLastChangeLoad 
	* @Description: 获取文件最后修改时间，有修改则会重新加载properties
	*/
	public static void getLastChangeLoad(){
		try {
			String path = PropertiesUtil.class.getResource("/resources/").toString()+"mapperjson.properties";
			if(path.indexOf("file:") >= 0){
				path = path.substring(path.indexOf("file:")+6, path.length());
			}
			File file = new File(path);
			long file_lastmodify = file.lastModified();
			Object obj = RedisCache.getCacheInstance().get(MSG_TEMPLATE_MODIFY_TIME);
			if(obj ==null){
				initProperties();
				//将修改时间放到缓存中
				RedisCache.getCacheInstance().put(MSG_TEMPLATE_MODIFY_TIME, file_lastmodify);
			}else{
				long redis_lastmodify = (long)obj;
				if(file_lastmodify != redis_lastmodify){
					//文件有修改
					initProperties();
					RedisCache.getCacheInstance().put(MSG_TEMPLATE_MODIFY_TIME, file_lastmodify);
				}
			}
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
		initProperties();
		getLastChangeLoad();
		System.out.println(getValue("test3.do"));
	}
}
