package com.phiz.common.utils;

import java.util.List;
import java.util.Map;

public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils{

	/**
	 * 方法描述: 判断一个对象是否为空 
	 * 
	 * @param obj
	 * @return 返回类型： boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String) {// 字符串
			return "".equals(obj) || "".equals(obj.toString().trim()) || "null".equals(obj.toString().trim());
		} else if (obj instanceof Map) {// 复合类型Map
			if (((Map) obj).size() == 0) {
				return true;
			}
		} else if (obj instanceof List) {// 复合类型List
			if (((List) obj).size() == 0) {
				return true;
			}
		} else if (obj instanceof String[]) {// 复合类型String[]
			if (((String[]) obj).length == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 方法描述: 判断一个对象是否不为空
	 * 
	 * @param obj
	 * @return 返回类型： boolean
	 */
	public static boolean isNotNullOrEmpty(Object obj) {
		return !isNullOrEmpty(obj);
	}
}
