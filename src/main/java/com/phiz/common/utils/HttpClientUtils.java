package com.phiz.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phiz.common.constance.Constance;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.utils.HttpClientUtils</b>
 * <b>Description: http 请求 工具类</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年6月14日 下午5:38:48</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年6月14日 下午5:38:48   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class HttpClientUtils {

	static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录

	/**
	 * 连接池的最大连接数
	 */
	static final int MAXTOTAL = 100;

	/**
	 * 每个路由的最大连接数
	 */
	static final int DEFAULTMAXPERROUTE = 100;

	// http 请求连接池
	// static PoolingHttpClientConnectionManager manager = null;

	/**
	 * 在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
	 */
	static final int VALIDATEAFTERINACTIVITY = 2 * 1000;

	/**
	 * 连接超时时间
	 */
	static final int CONNECTTIMEOUT = 200 * 1000;

	/**
	 * 等待数据超时时间
	 */
	static final int SOCKETTIMEOUT = 400 * 1000;

	/**
	 * 从连接池获取连接的等待超时时间
	 */
	static final int CONNECTIONREQUESTTIMEOUT = 3 * 1000;

	/**
	 * 连接存活时间，如果不设置，则根据长连接信息决定 单位秒
	 */
	static final int TIMETOLIVE = 3;

	static final int IDLECONNECTION = 60;

	static {

		// //创建池化连接管理器
		// manager = new
		// PoolingHttpClientConnectionManager(socketFactoryRegistry,connectionFactory,dnsResolver);
		//
		// //默认为Socket配置
		// SocketConfig defaultSocketConfig =
		// SocketConfig.custom().setTcpNoDelay(true).build();
		//
		// manager.setDefaultSocketConfig(defaultSocketConfig);
		//
		// manager.setMaxTotal(MAXTOTAL); //设置整个连接池的最大连接数
		// //每个路由的默认最大连接，每个路由实际最大连接数由DefaultMaxPerRoute控制，而MaxTotal是整个池子的最大数
		// //设置过小无法支持大并发(ConnectionPoolTimeoutException) Timeout waiting for connection
		// from pool
		// manager.setDefaultMaxPerRoute(DEFAULTMAXPERROUTE);//每个路由的最大连接数
		//
		// //在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
		// manager.setValidateAfterInactivity(VALIDATEAFTERINACTIVITY);
	}

	private static CloseableHttpClient getClient() {
		// 创建HttpClient
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManagerShared(false) // 连接池不是共享模式
				.evictIdleConnections(IDLECONNECTION, TimeUnit.SECONDS) // 定期回收空闲连接
				.evictExpiredConnections()// 定期回收过期连接
				.setConnectionTimeToLive(TIMETOLIVE, TimeUnit.SECONDS) // 连接存活时间，如果不设置，则根据长连接信息决定
				.setDefaultRequestConfig(getconfig()) // 设置默认请求配置
				.setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE) // 连接重用策略，即是否能keepAlive
				.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE) // 长连接配置，即获取长连接生产多长时间
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)) // 设置重试次数，默认是3次，当前是禁用掉（根据需要开启）
				.build();
		return httpClient;
	}

	private static HttpPost instancePost(String url) {
		HttpPost post = new HttpPost(url);
		post.setConfig(getconfig());
		return post;
	}

	private static HttpPut instacnePut(String url) {
		HttpPut put = new HttpPut(url);
		put.setConfig(getconfig());
		return put;
	}

	private static HttpGet instaceGet(String url) {
		HttpGet get = new HttpGet(url);
		get.setConfig(getconfig());
		return get;
	}

	private static RequestConfig getconfig() {
		// 默认请求配置
		RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(CONNECTTIMEOUT) // 设置连接超时时间，2s
				.setSocketTimeout(SOCKETTIMEOUT) // 设置等待数据超时时间，5s
				.setConnectionRequestTimeout(CONNECTIONREQUESTTIMEOUT) // 设置从连接池获取连接的等待超时时间
				.build();
		return defaultRequestConfig;
	}

	private static void setHeaders(HttpRequestBase base, Map<String, String> headers) {
		if (headers != null) {
			for (Entry<String, String> entry : headers.entrySet()) {
				base.setHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	private static UrlEncodedFormEntity setUrlEncodedEntity(Map<?, ?> params) throws UnsupportedEncodingException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Object name : params.keySet()) {
			nvps.add(new BasicNameValuePair(name.toString(), JsonUtil.toJson(params.get(name))));
		}
		return new UrlEncodedFormEntity(nvps);
	}

	private static StringEntity setStringEntity(String parms) {
		StringEntity entity = new StringEntity(parms, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		return entity;
	}

	private static String execute(HttpRequestBase http) throws Exception {
		// boolean isgizp = http.getAllHeaders()!=
		// null?http.getLastHeader(HttpHeaders.CONTENT_TYPE).getValue().indexOf("gzip")
		// != -1:false;
		CloseableHttpResponse result = null;
		CloseableHttpClient client = null;
		try {
			Object[] params = new Object[] { http.getMethod(), http.getURI() };
			if (http instanceof HttpEntityEnclosingRequestBase) {
				String temp = ((HttpEntityEnclosingRequestBase) http).getEntity() == null ? ""
						: EntityUtils.toString(((HttpEntityEnclosingRequestBase) http).getEntity());
				params = new Object[] { http.getMethod(), http.getURI(), temp };
			}
			logger.info("request {} url: {},params:{} start", params);
			client = getClient();
			result = client.execute(http);
			String str = "";
			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (result.getLastHeader(HttpHeaders.CONTENT_TYPE).getValue()
						.equals(ContentType.APPLICATION_OCTET_STREAM.getMimeType())) {
					InputStream is = result.getEntity().getContent();
					GZIPInputStream gzip = new GZIPInputStream(is);
					byte[] buf = new byte[1024];
					int num = -1;
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					while ((num = gzip.read(buf, 0, buf.length)) != -1) {
						baos.write(buf, 0, num);
					}
					byte[] byteArray = baos.toByteArray();
					baos.flush();
					baos.close();
					gzip.close();
					is.close();
					str = new String(byteArray, "utf-8");
				} else {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(result.getEntity(), "utf-8");
				}
				// 把json字符串转换成json对象
				logger.error("request {} url:{},end. result:{}", http.getMethod(), http.getURI(), str);
				return str;
			}
			if (result.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				String temp  = EntityUtils.toString(result.getEntity(), "utf-8");
				logger.error("request {} url:{},error. code:{}. result:{}", http.getMethod(), http.getURI(),result.getStatusLine().getStatusCode(), temp);
			}
		} catch (Exception e) {
			if (result != null) {
				result.close();
			}
			if (client != null) {
				client.close();
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (client != null) {
				client.close();
			}
		}
		logger.error("request {} url:{},error:{}", http.getMethod(), http.getURI());
		return null;
	}

	public static String post(String url, Map<String, Object> params) {
		return post(url, params, null);
	}

	public static String post(String url, Object params, Map<String, String> headers) {
		try {
			HttpPost post = instancePost(url);
			setHeaders(post, headers);
			boolean isJson = false;
			for (String val : headers.values()) {
				if (Constance.HEADER_CONTENT_TYPE_JSON_VAL.equals(val)) {
					isJson = true;
				}
			}

			HttpEntity entity = null;
			if (!isJson) {
				if (params instanceof Map) {
					entity = setUrlEncodedEntity(((Map<?, ?>) params));
				}
				entity = new ByteArrayEntity(params.toString().getBytes());
			}
			if (isJson) {
				entity = setStringEntity(params instanceof String ? params.toString() : JsonUtil.toJson(params));
			}
			post.setEntity(entity);
			String result = execute(post);
			return result;
		} catch (Exception e) {
			logger.error("http request error. url:{}, params:{},hearders:{},cause:{}", url, params,
					headers != null ? headers.toString() : "no header", e);
			return null;
		}
	}

	public static String post(String url, String params) {
		return post(url, params, null);
	}

	public static String post(String url, String params, Map<String, String> headers) {
		try {
			HttpPost post = instancePost(url);
			setHeaders(post, headers);
			AbstractHttpEntity entity = null;
			if (headers.containsKey(Constance.HEADER_CONTENT_TYPE_KEY)&& headers.get(Constance.HEADER_CONTENT_TYPE_KEY).equals(Constance.HEADER_CONTENT_TYPE_APPLICATION_XML_VAL)) {
				entity = new ByteArrayEntity(params.getBytes());
			} else {
				entity = setStringEntity(params);
			}
			post.setEntity(entity);
			return execute(post);
		} catch (Exception e) {
			logger.error("http request error. url:{}, params:{},hearders:{},cause:{}", url, params,
					headers != null ? headers.toString() : "no header", e);
			return null;
		}
	}

	public static String put(String url, String params) {
		try {
			HttpPut put = instacnePut(url);
			StringEntity entity = setStringEntity(params);
			put.setEntity(entity);
			return execute(put);
		} catch (Exception e) {
			logger.error("http request error. url:{}, params:{},cause:{}", url, params, e);
			return null;
		}
	}

	public static String put(String url, String params, Map<String, String> hedear) {
		try {
			HttpPut put = instacnePut(url);
			setHeaders(put, hedear);
			StringEntity entity = setStringEntity(params);
			put.setEntity(entity);
			return execute(put);
		} catch (Exception e) {
			logger.error("http request error. url:{}, params:{},cause:{}", url, params, e);
			return null;
		}
	}

	public static String get(String url) {
		return get(url, null);
	}

	public static String get(String url, String params, Map<String, String> header) {
		return get(url, null);
	}

	public static String get(String url, Object params, Map<String, String> header) {
		StringBuffer _url = new StringBuffer(url);
		if (params instanceof Map) {
			int index = 0;
			Map<?, ?> _params = ((Map<?, ?>) params);
			for (Entry<?, ?> entry : _params.entrySet()) {
				if (index == 0) {
					_url.append("?");
				}
				if (index > 0) {
					_url.append("&");
				}
				_url.append(entry.getKey());
				_url.append("=");
				_url.append(entry.getValue());
				index++;
			}
		}
		if (params instanceof String) {

		}
		return get(_url.toString(), header);
	}

	public static String get(String url, Map<String, String> header) {
		try {
			HttpGet get = instaceGet(url);
			setHeaders(get, header);
			return execute(get);
		} catch (Exception e) {
			logger.error("http request error. url:{},cause:{}", url, e);
			return null;
		}
	}

	// public static String put(String url,String params,Map<String, String> hedear)
	// {
	// try {
	// HttpPut put = getPut(url);
	// setHeaders(put, hedear);
	// StringEntity entity = setStringEntity(params);
	// put.setEntity(entity);
	// return execute(put);
	// } catch (Exception e) {
	// logger.error("http request error. url:{}, params:{},cause:{}",url,params,e);
	// return null;
	// }
	// }

}
