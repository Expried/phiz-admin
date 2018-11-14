/**
 * 
 */
package com.phiz.common.utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xiao.ai
 * 
 */
public class HttpClientUtil {

	/*private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(120000).setConnectTimeout(30000)
			.setConnectionRequestTimeout(10000).build();*/
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(120000).setConnectTimeout(120000)
			.setConnectionRequestTimeout(120000).build();

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 */
	public static String sendHttpPost(String httpUrl,CredentialsProvider provider) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		return sendHttpPost(httpPost,provider);
	}

	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param params
	 *            参数(格式:key1=value1&key2=value2)
	 */
	public static String sendHttpPost(String httpUrl, String params,CredentialsProvider provider) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost,provider);
	}
	
	public static String httpPost2Json(String httpUrl, String params,CredentialsProvider provider){
		
		HttpPost httpPost = new HttpPost(httpUrl);
		StringEntity entity;
		try {
			entity = new StringEntity(params,"utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost,provider);
	}
	
	
	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 */
	public static String sendHttpPost(String httpUrl, Map<String, String> maps,CredentialsProvider provider) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost,provider);
	}

	/**
	 * @Description post
	 * 时间:	2017年8月31日 下午4:45:34
	 * @param url
	 * @param params json
	 * @return
	 */
	public static String httpPostJson(String url,String params,CredentialsProvider provider){
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity;
		try {
			entity = new StringEntity(params,"utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost,provider);
	}

	/**
	 * 请求IBE+ POST请求
	 * @param url URL
	 * @param params 请求参数
	 * @param provider 账号
	 * @return 结果
	 */
	public static String httpPostToIBE(String url,String params,CredentialsProvider provider){
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity;
		try {
			entity = new StringEntity(params,"utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("text/plain");
			httpPost.setEntity(entity);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost,provider);
	}

	/**
	 * 发送Post请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpPost(HttpPost httpPost,CredentialsProvider provider) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			if(provider!=null){
				//进行http basic验证
				httpClient = HttpClients.custom().setDefaultCredentialsProvider(provider).build();
			}else{
				// 创建默认的httpClient实例.
				httpClient = HttpClients.createDefault();
			}
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch(SocketTimeoutException e){
			e.printStackTrace();
			//响应超过设置时间时 返回错误信息
			JSONObject error = new JSONObject();
			error.put("executeStatus", "FAIl_TIMEOUT");
			error.put("executeMsg", "Read timeout!接口响应超时,请联系管理员!");
			return error.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 */
	public static String sendHttpGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpGet(httpGet);
	}

	/**
	 * 发送 get请求Https
	 * 
	 * @param httpUrl
	 */
	public static String sendHttpsGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
		return sendHttpsGet(httpGet);
	}

	/**
	 * 发送Get请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * 发送Get请求Https
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpsGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
					.load(new URL(httpGet.getURI().toString()));
			DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(
					publicSuffixMatcher);
			httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}
	
	public static CredentialsProvider getProvider(String userName,String passWord){
		CredentialsProvider provider = new BasicCredentialsProvider();
	    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, passWord);
	    provider.setCredentials(AuthScope.ANY, credentials);
	    return provider;
	}
//	public static CredentialsProvider getProvider(){
//		CredentialsProvider provider = new BasicCredentialsProvider();
//	    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(UdxUtil.getUserName(), UdxUtil.getPassWord());
//	    provider.setCredentials(AuthScope.ANY, credentials);
//	    return provider;
//	}
}
