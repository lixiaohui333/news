package com.api.util.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**   
 * @Title: RedisProperties.java 
 * @Package com.gogotown.util.redis 
 * @Description: TODO redis连接池配置文件加载
 * @author zhang kui 
 * @date 2014-12-19 下午12:42:35 
 * @version V1.0   
 */
public class RedisProperties {
	private static Logger LOG = LoggerFactory.getLogger(RedisProperties.class);
	private static Map<String,String> map = new HashMap<String,String>();
	static{
		Properties properties = new Properties();
		try{
			properties.load(RedisProperties.class.getResourceAsStream("/resources/redis.properties"));
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
			LOG.error("加载redis启动配置文件报错",e.fillInStackTrace());
		}
	}
	
	/**
	* @author zhang kui   
	* @Title: getPropertiesValue 
	* @Description: TODO 获取配置文件数据
	* @param @param key 传入配置文件对于的key
	* @param @return    返回key值对于的value
	* @return String    返回类型 
	* @throws
	 */
	public static String getPropertiesValue(String key){
	
		return map.get(key);
	}
	
}


