package com.phiz.common.aspectj;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phiz.common.handler.LogHandler;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.aspectj.LogAspectj</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 下午6:03:59</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 下午6:03:59   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Component
@Aspect
public class LogAspectj {

	private static final Logger LOG = LoggerFactory.getLogger("web");

	@Autowired
	private  HttpServletRequest request;

	@AfterReturning(pointcut = " execution(* com..*.*Controller.*(..))",returning="rvt")
	public void afterMethod(JoinPoint point,Object rvt) {
		LOG.debug(" request url:{} end time:{} ms.,",request.getRequestURI(),System.currentTimeMillis()-LogHandler.reqestTime.get());
	}
}
