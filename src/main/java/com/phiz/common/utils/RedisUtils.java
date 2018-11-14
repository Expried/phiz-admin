package com.phiz.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.utils.RedisUtils</b>
 * <b>Description:Redis 操作工具类</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 下午5:35:21</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 下午5:35:21   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtils {

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/** 
	 * 
	*/
	public int DEFAULT_DB = 2;

	public final int ONE_DB = 1;

	public final int TWO_DB = 2;

	public final int THREE_DB = 3;

	public final int FOUR_DB = 4;

	@PostConstruct
	private void init() {
		JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
		DEFAULT_DB = jedisConnectionFactory.getDatabase();
	}

	public void changeDb(int db) {
		JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
		jedisConnectionFactory.setDatabase(db);
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
	}

	public void convertAndSend(String msg, String topic) {
		redisTemplate.convertAndSend(msg, topic);
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Set<String> keys(String keys) {
		return redisTemplate.keys(keys);
	}

	/**
	 * 写入缓存设置时效时间 默认为分钟
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			set(key, value, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存设置时效时间
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime, TimeUnit unit) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, TimeUnit.MILLISECONDS.convert(expireTime, unit), TimeUnit.MILLISECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	public <T> T getBean(String key, Class<T> claz) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return JsonUtil.toBean(result.toString(), claz);
	}

	/**
	 * 哈希 添加
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void hmSet(String key, Object hashKey, Object value) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		hash.put(key, hashKey, value);
	}

	/**
	 * 哈希 添加
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void hmSet(String key, Object hashKey, Object value, long time, TimeUnit unit) {
		hmSet(key, hashKey, value);
		redisTemplate.expire(key, TimeUnit.MICROSECONDS.convert(time, unit), TimeUnit.MILLISECONDS);
	}

	
	public void expire(String key,long time,TimeUnit unit) {
		redisTemplate.expire(key, TimeUnit.MICROSECONDS.convert(time, unit), TimeUnit.MILLISECONDS);
	}
	
	
	/**
	 * 删除哈希中的某个键值
	 */
	public void hdel(String key, Object hashKey) {
		redisTemplate.opsForHash().delete(key, hashKey);
	}

	/**
	 * 哈希获取数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public <T> T hmGet(String key, Object hashKey) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return (T) hash.get(key, hashKey);
	}

	public <T> List<T> hmGetAll(String key) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		Set<Object> keys = hash.keys(key);
		List<Object> data = hash.multiGet(key, keys);
		List<T> _data = new ArrayList<T>();
		for (Object obj : data) {
			T temp = (T) obj;
			_data.add(temp);
		}
		return _data;
	}

	public <T> T hmGetBean(String key, Object hashKey, Class<T> claz) {
		String json = hmGet(key, hashKey).toString();
		return JsonUtil.toBean(json, claz);
	}

	public <T> List<T> hmGetAllBean(String key, Class<T> claz) {
		List<Object> hashs = hmGetAll(key);
		List<T> data = new ArrayList<T>();
		for (Object temp : hashs) {
			T t = JsonUtil.toBean(temp.toString(), claz);
			data.add(t);
		}
		return data;
	}

	/**
	 * 列表添加
	 * 
	 * @param k
	 * @param v
	 */
	public void lPush(String k, Object v) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush(k, v);
	}

	/**
	 * 列表获取
	 * 
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public List<Object> lRange(String k, long l, long l1) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		return list.range(k, l, l1);
	}

	/**
	 * 列表获取
	 * 
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public <T> List<T> lRangeBean(String k, long l, long l1, Class<T> claz) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		List<Object> jsons = list.range(k, l, l1);
		List<T> data = new ArrayList<T>();
		for (Object object : jsons) {
			T t = JsonUtil.toBean(object.toString(), claz);
			data.add(t);
		}
		return data;
	}

	/**
	 * 集合添加
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add(key, value);
	}

	/**
	 * 集合获取
	 * 
	 * @param key
	 * @return
	 */
	public Set<Object> setMembers(String key) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		return set.members(key);
	}

	/**
	 * 有序集合添加
	 * 
	 * @param key
	 * @param value
	 * @param scoure
	 */
	public void zAdd(String key, Object value, double scoure) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add(key, value, scoure);
	}

	/**
	 * 有序集合获取
	 * 
	 * @param key
	 * @param scoure
	 * @param scoure1
	 * @return
	 */
	public <T> Set<T> rangeByScore(String key, double scoure, double scoure1) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		return (Set<T>) zset.rangeByScore(key, scoure, scoure1);
	}

	/**
	 * 
	 * <b>@param key</br>
	 * <b>@Throws:</br>
	 * <b>@Description:通过key 的前缀删除redis 缓存</br>
	 * <b>@Author:淳峰 1569812004@qq.com </br>
	 * <b>@Time:2018年6月25日上午10:26:13</br>
	 *
	 */
	public void delKeys(String key) {
		Set<String> keys = redisTemplate.keys(key + "*");
		for (String _key : keys) {
			deleteKey(_key);
		}
	}

	/**
	 * 
	 * <b>@param key</br>
	 * <b>@Throws:</br>
	 * <b>@Description:删除具体的key</br>
	 * <b>@Author:淳峰 1569812004@qq.com </br>
	 * <b>@Time:2018年6月25日上午10:26:39</br>
	 *
	 */
	public void deleteKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 
	 * <br>
	 * 
	 * @param key
	 *            <br>
	 * @param hash
	 *            <br>
	 * @return:</br>
	 * 				<br>
	 * @Description:判断hash key 是否存在</br>
	 *                     <br>
	 * @Author:淳峰 1569812004@qq.com </br>
	 *            <br>
	 * @Time:2018年7月24日下午3:17:58</br>
	 *
	 */
	public boolean hexist(String key, String hash) {
		HashOperations<String, Object, Object> _hash = redisTemplate.opsForHash();
		Set<Object> keys = _hash.keys(key);
		for (Object temp : keys) {
			if (temp.toString().equals(hash)) {
				return true;
			}
		}
		return false;
	}
}
