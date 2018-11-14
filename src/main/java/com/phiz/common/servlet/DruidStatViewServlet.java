/**
 * <pre>
 * <b>Project:hzfare</b>
 * <b>FiledName:com.hz.common.servlet.DruidStatViewServlet.java</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午2:40:16</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午2:40:16   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
package com.phiz.common.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare</b>
 * <b>ClassName:com.hz.common.servlet.DruidStatViewServlet</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午2:40:16</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午2:40:16   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@WebServlet(urlPatterns = "/druid/*", 
initParams={
//        @WebInitParam(name="allow",value="192.168.16.110,127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
//        @WebInitParam(name="deny",value="192.168.16.111"),// IP黑名单 (存在共同时，deny优先于allow)
        @WebInitParam(name="loginUsername",value="admin"),// 用户名
        @WebInitParam(name="loginPassword",value="admin"),// 密码
        @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
}) 
public class DruidStatViewServlet extends StatViewServlet {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

}
