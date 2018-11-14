package com.phiz.common.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.phiz.common.constance.Constance;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.utils.BigDecimalValueFilter</b>
 * <b>Description:fastjson bigdecimal 处理类</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年8月22日 上午9:13:48</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年8月22日 上午9:13:48   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class BigDecimalSerializer implements ObjectSerializer {

	@Override
	public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)throws IOException {
		if (object == null) {
    		serializer.out.writeNull();
    		return;
    	}
    	
		BigDecimal val = (BigDecimal) object;
		DecimalFormat format = new DecimalFormat(Constance.NUMBER_FORMAT_STR);
        serializer.write(format.format(val.doubleValue()));
	}
	
	

	
}
