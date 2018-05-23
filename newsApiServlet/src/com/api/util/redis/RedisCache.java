package com.api.util.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.jboss.serial.io.JBossObjectInputStream;
import org.jboss.serial.io.JBossObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**   
 * @Title: RedisCache.java 
 * @Package com.gogotown.util.redis 
 * @Description: TODO RedisCache,redis客户端缓存实现接口
 * @author zhang kui 
 * @date 2014-12-18 上午11:19:37 
 * @version V1.0   
 */
public class RedisCache implements GoGoDownCache {
	private static Logger LOG = LoggerFactory.getLogger(RedisCache.class);
    private final static String HOST;
    private final static int PORT;
    private final static int TIMEOUT;
    private final static JedisPool JEDIS_POOL;
    private static RedisCache redisCache  = new RedisCache();;
    private final static String REDIS_PASS;

    static {
        HOST = RedisProperties.getPropertiesValue("HOST");
        PORT = Integer.parseInt(RedisProperties.getPropertiesValue("PORT"));
        REDIS_PASS = RedisProperties.getPropertiesValue("REDIS_PASS");
        TIMEOUT = Integer.valueOf(RedisProperties.getPropertiesValue("TIMEOUT"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.valueOf(RedisProperties.getPropertiesValue("MAXTOTAL")));
        jedisPoolConfig.setMaxIdle(Integer.valueOf(RedisProperties.getPropertiesValue("MAXIDLE")));
        jedisPoolConfig.setMinIdle(Integer.valueOf(RedisProperties.getPropertiesValue("MINIDLE")));
        if(StringUtils.isNotBlank(REDIS_PASS)){
        	JEDIS_POOL = new JedisPool(jedisPoolConfig, HOST, PORT,TIMEOUT,REDIS_PASS);
        }else{
        	JEDIS_POOL = new JedisPool(jedisPoolConfig, HOST, PORT,TIMEOUT);
        }
    }
    

    public static RedisCache getCacheInstance() {
           
        return redisCache;
    }


    private RedisCache() {

    }


    /**
     * {@inheritDoc}
     */
    public void put(String key, Object value) {
        put(key, value, -1);
    }


    /**
     * {@inheritDoc}
     */
    public void put(String key, Object value, int expire) {
        if (key == null)
            return;
        if (value == null)
            return;
        Jedis jedis = acquireConnection();
        try {
            byte[] keyByte = key.getBytes();
            if (value instanceof String) {
                String cacheValue = (String) value;
                jedis.set(key, cacheValue);
            }
            else {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                JBossObjectOutputStream jos = new JBossObjectOutputStream(bos);
                jos.writeObject(value);
                jos.close();
                jedis.set(keyByte, bos.toByteArray());
            }

            if (expire > 0)
                jedis.expire(keyByte, expire);
        }
        catch (IOException e) {
        	LOG.error("设置对象缓存数据报错,key:"+key,e.fillInStackTrace());
        }
        finally {
            if (jedis != null) {
                JEDIS_POOL.returnResource(jedis);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 字符串也用JBossObjectOutputStream 缓存
     */
    public void putObject(String key, Object value, int expire) {
        if (key == null)
            return;
        if (value == null)
            return;
        Jedis jedis = acquireConnection();
        try {
            byte[] keyByte = key.getBytes();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            JBossObjectOutputStream jos = new JBossObjectOutputStream(bos);
            jos.writeObject(value);
            jos.close();
            jedis.set(keyByte, bos.toByteArray());

            if (expire > 0)
                jedis.expire(keyByte, expire);
        }
        catch (IOException e) {
        	LOG.error("设置对象缓存数据报错,key:"+key,e.fillInStackTrace());
        }
        finally {
            if (jedis != null) {
                JEDIS_POOL.returnResource(jedis);
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    public Object get(String key) {
        Jedis jedis = acquireConnection();
        try {
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null && bytes.length > 0) {
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                JBossObjectInputStream jois = new JBossObjectInputStream(bais);
                Object obj = (Object) jois.readObject();
                jois.close();
                return obj;
            }
            return null;
        }
        catch (IOException e) {
        	LOG.error("获取对象缓存数据报错,key:"+key,e.fillInStackTrace());
            return null;
        }
        catch (ClassNotFoundException e) {
        	LOG.error("获取对象缓存数据没有找到,key:"+key,e.fillInStackTrace());
            return null;
        }
        finally {
            if (jedis != null) {
                JEDIS_POOL.returnResource(jedis);
            }
        }
    }


    public String getCacheString(String key) {
        Jedis jedis = acquireConnection();
        try {
            return jedis.get(key);
        }
        finally {
            if (jedis != null) {
                JEDIS_POOL.returnResource(jedis);
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    public void delete(String key) {
        Jedis jedis = acquireConnection();
        try {

            jedis.del(key.getBytes());

        }
        finally {
            if (jedis != null) {
                JEDIS_POOL.returnResource(jedis);
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    public void clearAll() {
        // TODO
    }


    public Jedis acquireConnection() {
        Jedis jedis = JEDIS_POOL.getResource();
        return jedis;
    }
    
    public void remove(String key){
    	Jedis jedis = acquireConnection();
    	String obj = jedis.get(key);
    	if(obj != null)
    		jedis.del(key);
    }
    
    /**
     * 
    * @author zhang kui   
    * @Title: main 
    * @Description: TODO demo测试代码，熟悉后删掉 
    * @param @param args    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public static void main(String[] args) {
//    	RedisCache.getCacheInstance().put("clickId", 12+"",2*60);
//    	RedisCache.getCacheInstance().put(SysConstants.ADMIN_LOGIN_IN_SESSION, null);
//    	System.out.println(RedisCache.getCacheInstance().get(SysConstants.ADMIN_LOGIN_IN_SESSION));
	}

}
