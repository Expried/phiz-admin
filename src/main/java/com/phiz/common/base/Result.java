package com.phiz.common.base;

import java.io.Serializable;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.base.Result</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午10:53:38</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午10:53:38   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 操作代码
	 */
	private Long code;

	/**
	 * 提示语
	 */
	private String msg;

	/**
	 * 数据对象
	 */
	private Object data;
	

	public Result(long code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Result(long code, String msg, Object t) {
		this.code = code;
		this.msg = msg;
		this.data = t;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
