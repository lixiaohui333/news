package com.api.util.redis;

/**   
 * @Title: GoGoDownCache.java 
 * @Package com.gogotown.util.redis 
 * @Description: TODO redis缓存接口
 * @author zhang kui 
 * @date 2014-12-18 上午11:19:37 
 * @version V1.0   
 */
public interface GoGoDownCache {
    /**
     * 缓存对象
     * 
     * @param key
     *            键
     * @param value
     *            对象
     */
    void put(String key, Object value);


    /**
     * 缓存对象
     * 
     * @param key
     *            键
     * @param value
     *            对象
     * @param expire
     *            过期时间[指定多少秒后过期]
     */
    void put(String key, Object value, int expire);


    /**
     * 根据键获得缓存中的对象
     * 
     * @param key
     *            键
     * @return 如果存在，返回对象；否则，返回<code>null</code>
     */
    Object get(String key);
    
    
    /**
     * 根据键获得缓存中的字符串
     * 
     * @param key
     *            键
     * @return 如果存在，返回对象；否则，返回<code>null</code>
     */
    String getCacheString(String key);


    /**
     * 根据键删除缓存中的对象
     * 
     * @param key
     * @return
     */
    void delete(String key);


    /**
     * 清空缓存
     */
    void clearAll();
}
