package com.phiz.common.utils;
//package com.hz.fare.common.utils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import io.codis.jodis.JedisResourcePool;
//import io.codis.jodis.RoundRobinJedisPool;
//import redis.clients.jedis.Jedis;
//
//@Component
//@Configuration
//@EnableCaching
//public class JodisUtils extends CachingConfigurerSupport {
//
//	private JedisResourcePool jedisPool;
//
//	@Value("${spring.jodis.url}")
//	private String url;
//
//	@Value("${spring.jodis.zk}")
//	private String zk;
//
//	@Value("${spring.jodis.password}")
//	private String password;
//
//	@Value("${spring.jodis.db}")
//	private int db;
//
//	@Value("${spring.jodis.timeout}")
//	private int timeout;
//
//	@Value("${spring.profiles.active}")
//	private String file;
//
//	@Value("${spring.redis.url}")
//	private String redisUrl;
//
//	@Value("${spring.redis.port}")
//	private int port;
//
//	@Value("${spring.redis.password}")
//	private String _password;
//
//	@Value("${spring.redis.database}")
//	private String redisDb;
//	
//		
//	
//	@PostConstruct
//	private void init() {
//		jedisPool = RoundRobinJedisPool.create().curatorClient(url, timeout).zkProxyDir(zk).password(password).build();
//	}
//
//	public Jedis getJedis() {
//		Jedis jedis = null;
//		if (file.equals("dev")) {
//			jedis = new Jedis(redisUrl, port);
//			if (StringUtil.isNotEmpty(_password)) {
//				jedis.auth(_password);
//			}
//			if (StringUtil.isNotEmpty(redisDb)) {
//				jedis.select(Integer.parseInt(redisDb));
//			}
//			return jedis;
//		}
//		jedis = jedisPool.getResource();
//		jedis.select(db);
//		return jedis;
//	}
//
//	/**
//	 * 写入缓存设置时效时间 默认为分钟
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean set(final String key, Object value, Long expireTime) {
//		boolean result = false;
//		try {
//			set(key, value, expireTime, TimeUnit.SECONDS);
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 写入缓存设置时效时间 默认为分钟
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean set(final String key, Object value) {
//		boolean result = false;
//		Jedis jedis = getJedis();
//		try {
//			jedis.set(key, JsonUtil.toJson(value));
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			jedis.close();
//		}
//		return result;
//	}
//
//	/**
//	 * 写入缓存设置时效时间
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean set(final String key, Object value, Long expireTime, TimeUnit unit) {
//		boolean result = false;
//		Jedis jedis = getJedis();
//		try {
//			String val = value.toString();
//			if (value.getClass().getName().toLowerCase().indexOf("string") ==-1) {
//				val = JsonUtil.toJson(value);
//			}
//			jedis.set(key, val);
//			jedis.pexpire(key, TimeUnit.MILLISECONDS.convert(expireTime, unit));
//			// jedis.expire(key, expireTime, unit);
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			jedis.close();
//		}
//		return result;
//	}
//
//	public Long expire(String key, long time, TimeUnit unit) {
//		Jedis jedis = getJedis();
//		return jedis.expire(key, Integer.parseInt(TimeUnit.SECONDS.convert(time, unit) + ""));
//	}
//
//	/**
//	 * 批量删除对应的value
//	 * 
//	 * @param keys
//	 */
//	public void remove(final String... keys) {
//		for (String key : keys) {
//			remove(key);
//		}
//	}
//
//	/**
//	 * 批量删除key
//	 * 
//	 * @param pattern
//	 */
//	public void removePattern(final String pattern) {
//		Jedis jedis = getJedis();
//		Set<String> keys = jedis.keys(pattern);
//		if (keys.size() > 0) {
//			jedis.del((String[]) keys.toArray());
//		}
//		jedis.close();
//	}
//
//	/**
//	 * 删除对应的value
//	 * 
//	 * @param key
//	 */
//	public void remove(final String key) {
//		Jedis jedis = getJedis();
//		if (exists(key)) {
//			jedis.del(key);
//		}
//	}
//
//	/**
//	 * 判断缓存中是否有对应的value
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public boolean exists(final String key) {
//		Jedis jedis = getJedis();
//		boolean exist = jedis.exists(key);
//		jedis.close();
//		return exist;
//	}
//
//	/**
//	 * 读取缓存
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public Object get(final String key) {
//		Jedis jedis = getJedis();
//
//		Object data = jedis.get(key);
//		jedis.close();
//		return data;
//	}
//
//	public <T> T getBean(String key, Class<T> t) {
//		Jedis jedis = getJedis();
//
//		String data = jedis.get(key);
//		jedis.close();
//		if (StringUtil.isEmpty(data)) {
//			return null;
//		}
//		return JsonUtil.toBean(data, t);
//	}
//
//	/**
//	 * 哈希 添加
//	 * 
//	 * @param key
//	 * @param hashKey
//	 * @param value
//	 */
//	public void hmSet(String key, Object hashKey, Object value) {
//		Jedis jedis = getJedis();
//		jedis.hset(key, hashKey.toString(), JsonUtil.toJson(value));
//		jedis.close();
//	}
//
//	/**
//	 * 删除哈希中的某个键值
//	 */
//	public void hdel(String key, Object hashKey) {
//		Jedis jedis = getJedis();
//		jedis.hdel(key, hashKey.toString());
//		jedis.close();
//	}
//
//	/**
//	 * 哈希获取数据
//	 * 
//	 * @param key
//	 * @param hashKey
//	 * @return
//	 */
//	public <T> T hmGet(String key, String hashKey, Class<T> claz) {
//		Jedis jedis = getJedis();
//		String data = jedis.hget(key, hashKey.toString());
//		jedis.close();
//		if (data != null) {
//			return JsonUtil.toBean(data, claz);
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * <b>@param key</br>
//	 * <b>@Throws:</br>
//	 * <b>@Description:通过key 的前缀删除redis 缓存</br>
//	 * <b>@Author:淳峰 1569812004@qq.com </br>
//	 * <b>@Time:2018年6月25日上午10:26:13</br>
//	 *
//	 */
//	public void delKeys(String key) {
//		Jedis jedis = getJedis();
//		Set<String> keys = jedis.keys(key + "*");
//		for (String _key : keys) {
//			deleteKey(_key);
//		}
//		jedis.close();
//	}
//
//	/**
//	 * 
//	 * <b>@param key</br>
//	 * <b>@Throws:</br>
//	 * <b>@Description:删除具体的key</br>
//	 * <b>@Author:淳峰 1569812004@qq.com </br>
//	 * <b>@Time:2018年6月25日上午10:26:39</br>
//	 *
//	 */
//	public void deleteKey(String key) {
//		Jedis jedis = getJedis();
//		jedis.del(key);
//		jedis.close();
//	}
//
//	public <T> List<T> hmGetAll(String key, Class<T> claz) {
//		Jedis jedis = getJedis();
//		Map<String, String> data = jedis.hgetAll(key);
//		List<T> _data = new ArrayList<T>();
//		for (String val : data.values()) {
//			T t = JsonUtil.toBean(val, claz);
//			_data.add(t);
//		}
//		jedis.close();
//		return _data;
//	}
//
//	//
//	// public <T> List<T> hmGetAll(String key,Class<T> claz){
//	// Set<String> keys = jedis.keys(key);
//	// List<T> _data = new ArrayList<T>();
//	// for (String temp : keys) {
//	// String data = jedis.get(temp);
//	// T t = JsonUtil.toBean(data, claz);
//	// _data.add(t);
//	// }
//	//// return _data;
//	// return null;
//	// }
//
//}
