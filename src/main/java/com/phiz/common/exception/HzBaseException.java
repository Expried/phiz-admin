package com.phiz.common.exception;

import com.phiz.common.dict.ErrorCode;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.exception.HzBaseException</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午8:58:11</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午8:58:11   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public  class HzBaseException  extends  Throwable{

	private static final long serialVersionUID = 1L;

	/** 
	 * 错误代码
	*/ 
	private Integer code;
	
	/** 
	 * 错误信息描述
	*/ 
	private String msg;
	
	
	
	

	public HzBaseException() {
		super();
	}

	
	public HzBaseException(ErrorCode error) {
		super(error.getMsg());
		this.code = error.getCode();
		this.msg = error.getMsg();
	}
	
	public HzBaseException(int code ,String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
