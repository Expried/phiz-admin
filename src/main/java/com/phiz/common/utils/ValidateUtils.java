package com.phiz.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.hibernate.validator.HibernateValidator;
import org.springframework.util.CollectionUtils;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.utils.ValidateUtils</b>
 * <b>Description:参数验证工具类</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年6月1日 上午9:23:11</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年6月1日 上午9:23:11   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public abstract class ValidateUtils {
	
	/**
	 * 使用hibernate的注解来进行验证
	 * 
	 */
	private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.utils .ValidateUtils</b>
	 * <b>@param obj
	 * <b>@return:验证结果</b>
	 * <b>@Description: 验证整个实体</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年6月1日上午9:36:41</b>    
	 *
	 */
	public static <T> ValidateResult validate(T obj) {
		ValidateResult result = new ValidateResult();
		result.setHasErrors(false);
		if (validator == null || obj == null ) {
			return result;
		}
		Set<ConstraintViolation<T>> violations = validator.validate(obj);
		Map<String, String> message = new HashMap<String, String>();
		if (!CollectionUtils.isEmpty(violations)) {
			result.setHasErrors(true);
			for (ConstraintViolation<T> temp : violations) {
				message.put(temp.getPropertyPath().toString(), temp.getMessage());
			}
		}
		result.setErrorMsg(message);
		return result;
	}

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.utils .ValidateUtils</b>
	 * <b>@param obj  验证对象
	 * <b>@param propertyName  验证属性名称
	 * <b>@return: 验证结果</b>
	 * <b>@Description:验证单个属性</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年6月1日上午9:37:24</b>    
	 *
	 */
	public static <T> ValidateResult validateProperty(T obj, String propertyName) {
		ValidateResult result = new ValidateResult();
		Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
		if (!CollectionUtils.isEmpty(set)) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(propertyName, cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}

}
