package com.phiz.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.phiz.common.handler.LogHandler;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.config.InterceptorConfig</b>
 * <b>Description:拦截器配置</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 下午7:22:53</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 下午7:22:53   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	 @Override  
	    public void addInterceptors(InterceptorRegistry registry) {  
	        //这里可以添加多个拦截器  
	        registry.addInterceptor(new LogHandler()).addPathPatterns("/**");  
	        super.addInterceptors(registry);  
	    }  
	
}
