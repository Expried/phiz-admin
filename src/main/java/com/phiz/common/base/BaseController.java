package com.phiz.common.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phiz.common.dict.ErrorCode;
import com.phiz.common.exception.HzBaseException;
import com.phiz.common.utils.JsonUtil;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.base.BaseController</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午11:00:14</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午11:00:14   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class BaseController {

	protected static final Logger LOG = LoggerFactory.getLogger("web");
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseController</b>
	 * <b>@param data 返回数据
	 * <b>@return:</b>
	 * <b>@Description:成功返回</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年5月30日上午11:02:51</b>    
	 *
	 */
	public static Result SUCCESS(Object data) {
		return new Result(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
	}
	
	public static Result SUCCESS() {
		return new Result(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg());
	}
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseController</b>
	 * <b>@return:</b>
	 * <b>@Description: 系统错误返回</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年5月30日上午11:03:17</b>    
	 *
	 */
	public static Result ERROR() {
		return new Result(ErrorCode.ERROR.getCode(), ErrorCode.ERROR.getMsg());
	}
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseController</b>
	 * <b>@param code 错误代码
	 * <b>@param msg  错误信息
	 * <b>@return:</b>
	 * <b>@Description:错误返回</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年5月30日上午11:04:07</b>    
	 *
	 */
	public static Result ERROR(long code,String msg) {
		return new Result(code,msg);
	}
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseController</b>
	 * <b>@param e
	 * <b>@return: 结构对象</b>
	 * <b>@Description:错误返回</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年5月30日上午11:05:08</b>    
	 *
	 */
	public static Result ERROR(HzBaseException e) {
		return new Result(e.getCode(), e.getMsg());
	}
	
    /**
     * 
     * <b>@Title:com.hz.fare.common.base .BaseController</b>
     * <b>@param req
     * <b>@param e
     * <b>@return:</b>
     * <b>@Description:统一异常处理</b>
     * <b>@Author:淳峰    1569812004@qq.com </b> 
     * <b>@Time:2018年6月11日上午11:48:35</b>    
     *
     */
    @ExceptionHandler(value = Throwable.class)  
    @ResponseBody  
    public Result jsonExceptionHandler(HttpServletRequest req, Throwable e) {
       LOG.error("request url:{} error. params:{},cause:{}",req.getRequestURI(),JsonUtil.toJson(req.getParameterMap()),e);
       if (e instanceof HzBaseException) {
		 return ERROR((HzBaseException)e);
       }
      return ERROR();  
    }  
    
    public Map<String, Object> sucess() {
		Map<String, Object> map = new HashMap<String,Object>();
		Result result = new Result(0, "OK");
		map.put("errorCode",result.getCode());
		map.put("errorMsg", result.getMsg());		
		return map;
	}
    
    public Map<String, Object> error(String e) {
		Map<String, Object> map = new HashMap<String,Object>();
		Result result = new Result(1, e);
		map.put("errorCode",result.getCode());
		map.put("errorMsg", result.getMsg());		
		return map;
	}
	
}
