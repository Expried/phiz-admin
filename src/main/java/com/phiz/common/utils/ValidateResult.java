package com.phiz.common.utils;

import java.util.Map;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.utils.ValidateResult</b>
 * <b>Description:参数验证结果</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年6月1日 上午9:19:09</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年6月1日 上午9:19:09   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class ValidateResult {

	// 校验结果是否有错
	private boolean hasErrors;

	// 校验错误信息
	private Map<String, String> errorMsg;

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Map<String, String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg=" + errorMsg + "]";
	}
}
