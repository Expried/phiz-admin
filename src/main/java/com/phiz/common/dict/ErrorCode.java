package com.phiz.common.dict;


/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.dict.ErrorCode</b>
 * <b>Description: 错误代码管理</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午9:01:39</b>0 
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午9:01:39   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public enum ErrorCode {

	//===================================
	//|  OTA 接口请求错误代码
	//===================================
	/** 
	 * 渠道编码器错误
	*/ 
	ENCODER_NOT_FOUND(-1001,"渠道  Encoder 未找到"),
	
	/** 
	 * 渠道编码器错误
	*/ 
	DECODER_NOT_FOUND(-1002,"渠道  Decoder 未找到"),
	
	
	/** 
	 * 渠道编码器错误
	*/ 
	PLATFORM_CONFIG_NOT_FOUND(-1003,"平台 配置未找到"),
	
	/** 
	 * 接口配置未找到
	*/ 
	INTERFACE_CONFIG_NOT_FOUND(-1004,"接口配置未找到"),
	

	
	/** 
	 * 出票相关错误
	*/ 
	TICKET_ISSUE_ERROR(-2001,"请求出票错误"),
	
	
	
	VERIFY_CHANGE(-3001,"运价变更"),
	
	VERIFY_SOURCE_ERROR(-3002,"供应商请求错误"),
	
	VERRIFY_MAXSEAT_ERROR(-3003,"舱位不足"),
	
	
	
    TICKET_PUSH_ORDER_NOT_FOUND(-4001,"对应订单没找到"),	
	
    
    FLIGHT_RESTRICT(-5001,"航线受限"),
    
    
	
	/** 
	 * 未知错误
	*/ 
	ERROR(-1,"未知错误"),
	
	
	
	/** 
	 * 参数错误
	*/ 
	PARAMS_ERROR(-2,"参数错误"),
	
	/** 
	 * 
	*/ 
	SUCCESS(200,""),
	
	/**
	 * 
	 */
	INSTER_ERROR(-3,"添加失败"),
	
	/**
	 * 
	 */
	DELETE_ERROR(-4,"删除失败"),
	
	/**
	 * 
	 */
	UPDATE_ERROR(-5,"更新失败"),
	
	DECODE_ERROR(-6,"解析fareList节点出现错误！"),
	
	TIMEOUT_ERROR(-7,"timeout"),
	
	RESULT_ERROR(-8,"result error");
	/** 
	 * 错误代码
	*/ 
	private int code;
	

	/** 
	 * 提示语句
	*/ 
	private String msg;
	
	
	
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.dict .ErrorCode</b>
	 * <b>@param code
	 * <b>@param msg</b>
	 * <b>@return:</b>
	  
	 * <b>@Description:</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年5月30日上午9:02:36</b>    
	 *
	 */
	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
