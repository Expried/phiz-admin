/**
 * <pre>
 * <b>Project:hzfare-core</b>
 * <b>FiledName:com.hz.fare.common.utils.JsonUtils.java</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午6:57:33</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午6:57:33   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
package com.phiz.common.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare</b>
 * <b>ClassName:com.hz.fare.rpc.DubboServiceImpl</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午4:02:45</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午4:02:45   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public abstract class JsonUtil {

	/**
	 * 空对象的的JSON字符串默认值: '{}'.
	 */
	public static final String EMPTY_JSON = "{}";

	protected static String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";
	protected static SerializeConfig mapping = new SerializeConfig();

	static {
		defaultDateFormat = "yyyy-MM-dd HH:mm:ss";
		mapping.put(Date.class, new SimpleDateFormatSerializer(defaultDateFormat));
		mapping.put(BigDecimal.class, new BigDecimalSerializer());
	}

	/**
	 * 受保护的构造方法, 防止外部构建对象实例.
	 */
	protected JsonUtil() {
		super();
	}

	/**
	 * 将对象实例obj转为json格式字符串;<br/>
	 * 当null==obj时, 则返回 null.
	 * 
	 * @param obj
	 *            对象实例.
	 * @return String json格式字符串.
	 */
	public static String toJson(Object obj) {
		if (null == obj) {
			return null;
		}
		return JSON.toJSONString(obj, mapping);
	}

	/**
	 * 将json格式字符串转为Javabean;<br/>
	 * 当null==str或str为无效json格式字符串时, 则返回 null.
	 * 
	 * @param str
	 *            json格式字符串.
	 * @return JSONObject json封装对象实例.
	 */
	public static JSONObject toBean(String str) {
		if (StringUtil.hasText(str)) {
			try {
				return JSON.parseObject(str);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将json格式字符串转为指定类型的Javabean;<br/>
	 * 当null==str或str为无效json格式字符串时, 则返回 null.
	 * 
	 * @param str
	 *            json格式字符串.
	 * @param clazz
	 *            对象的Class 类型
	 * @return T 对应Javabean实例.
	 */
	public static <T> T toBean(String str, Class<T> clazz) {
		if (StringUtil.hasText(str)) {
			try {
				return JSON.parseObject(str, clazz);
			} catch (JSONException e) {
			}
		}
		return null;
	}

	/**
	 * 将json格式字符串转为指定泛型的List;<br/>
	 * 当null==str或str为无效json格式字符串时, 则返回 null.
	 * 
	 * @param str
	 *            json格式字符串.
	 * @param clazz
	 *            对象的Class 类型
	 * @return T 对应泛型的List实例.
	 */
	public static <T> List<T> toList(String str, Class<T> clazz) {
		if (StringUtil.hasText(str)) {
			try {
				return JSON.parseArray(str, clazz);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将json格式字符串转为指定类型的Object;<br/>
	 * 当null==str或str为无效json格式字符串时, 则返回 null.<br/>
	 * 例：User dto = JsonUtil.toOject(json, new TypeReference<User>() {});
	 * 
	 * @param str
	 *            json格式字符串.
	 * @param clazz
	 *            对象的Class 类型
	 * @return T 对应Object实例.
	 */
	public static <T> T toOject(String str, TypeReference<T> type) {
		if (StringUtil.hasText(str)) {
			try {
				return JSON.parseObject(str, type);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 利用Json序列化和反序列化实现任意对象深度克隆.<br/>
	 * 例如: Resources res = JsonUtil.clone(tres, new
	 * TypeReference&ltResources&gt(){});
	 * 
	 * @param <T>
	 * @param obj
	 * @param type
	 * @return
	 */
	public static <T> T clone(Object obj, TypeReference<T> type) {
		if (null != obj) {
			String json = toJson(obj);
			try {
				return JSON.parseObject(json, type);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String str = "";
		System.out.println(JsonUtil.toJson(str));
	}
}