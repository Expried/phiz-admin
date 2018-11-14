package com.phiz.common.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.config.ListenerConfig</b>
 * <b>Description:监听器配置</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月31日 下午5:38:23</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月31日 下午5:38:23   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Configuration
public class ListenerConfig {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
		ServletListenerRegistrationBean slrBean = new ServletListenerRegistrationBean();
		slrBean.setListener(new RequestContextListener());
		return slrBean;
	}
}
