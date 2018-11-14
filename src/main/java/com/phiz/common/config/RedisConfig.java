package com.phiz.common.config;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phiz.common.utils.StringUtil;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.config.RedisConfig</b>
 * <b>Description:Redis 配置</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 下午5:28:27</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 下午5:28:27   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	private static final Logger LOG = LoggerFactory.getLogger("redisConfig");

	@Value("${spring.redis.url}")
	private String url;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.database}")
	private int database;

	/**
	 * 生成key的策略
	 * 
	 * @return
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/**
	 * 管理缓存
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		return rcm;
	}

	/**
	 * RedisTemplate配置
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(new FastJson2JsonRedisSerializer<Object>(Object.class));
		template.setHashValueSerializer(new FastJson2JsonRedisSerializer<Object>(Object.class));
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public RedisConnectionFactory jedisConnectionFactory() {
		initLog();
		JedisConnectionFactory jcf = new JedisConnectionFactory();
		jcf.setHostName(url);
		jcf.setPort(port);
		jcf.setDatabase(database);
		if (!StringUtil.isEmpty(password)) {
			jcf.setPassword(password);
		}
		jcf.setTimeout(800000);
		return jcf;
	}

	@Bean
	public RedisMessageListenerContainer contairner(RedisConnectionFactory factory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(factory);
		return container;
	}

//	/*
//	 * Redis消息监听器容器 这个容器加载了RedisConnectionFactory和消息监听器
//	 */
//	@Bean
//	 RedisMessageListenerContainer container(RedisConnectionFactory
//	 connectionFactory, ){
//	 RedisMessageListenerContainer container = new
//	 RedisMessageListenerContainer();
//	 container.setConnectionFactory(connectionFactory);
//	 container.addMessageListener(listenerAdapter, new
//	 PatternTopic("__key*__:expired"));
//	 return container;
//	 }
//
//	/*
//	 * 将Receiver注册为一个消息监听器，并指定消息接收的方法（receiveMessage）
//	 * 如果不指定消息接收的方法，消息监听器会默认的寻找Receiver中的handleMessage这个方法作为消息接收的方法
//	 */
//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMessage");
//	}
//
//	/*
//	 * Receiver实例
//	 */
//	@Bean
//	Receiver receiver(CountDownLatch latch) {
//		return new Receiver(latch);
//	}
//
//	@Bean
//	CountDownLatch latch() {
//		return new CountDownLatch(1);
//	}

	private void initLog() {
		LOG.info("------------------------------------------------------------------");
		LOG.info(" Redis Config Init ........");
		LOG.info(" url:{}", this.url);
		LOG.info(" password:{}", this.password);
		LOG.info(" port:{}", this.port);
		LOG.info("------------------------------------------------------------------");
	}

	// class Receiver {
	//
	// private CountDownLatch latch;
	//
	// @Autowired
	// public Receiver(CountDownLatch latch) {
	// this.latch = latch;
	// }
	//
	// public void receiveMessage(String message) {
	// latch.countDown();
	// }
	//
	// }

}
