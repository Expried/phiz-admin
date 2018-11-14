package com.phiz.common.handler;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.handler.LogHandler</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 下午7:17:38</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 下午7:17:38   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class LogHandler extends HandlerInterceptorAdapter {

	private static final Logger LOG = LoggerFactory.getLogger("web");
	

	public static ThreadLocal<Long> reqestTime = new ThreadLocal<>();
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		Enumeration<String> heads = request.getHeaderNames();
		JSONObject header = new JSONObject();
		while (heads.hasMoreElements()) {
			String _head =  heads.nextElement();
			Enumeration<String> val = request.getHeaders(_head);
			StringBuffer temp = new StringBuffer();
			while (val.hasMoreElements()) {
				String _val=  val.nextElement();
				temp.append(_val);
			}
			header.put(_head, temp.toString());
		}
		LOG.debug("request url:{},header:{} start",request.getRequestURI(),header.toJSONString());
		reqestTime.set(System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	
}
