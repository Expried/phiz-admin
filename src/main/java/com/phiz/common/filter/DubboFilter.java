package com.phiz.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.phiz.common.utils.JsonUtil;
import com.phiz.common.utils.ValidateResult;
import com.phiz.common.utils.ValidateUtils;


/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.filter.DubboFilter</b>
 * <b>Description:Dubbo Filter 过滤 </b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年6月1日 下午3:26:58</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年6月1日 下午3:26:58   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Activate(group = Constants.PROVIDER, value = "dubboFilter")
public class DubboFilter implements Filter{

	private static final Logger LOG = LoggerFactory.getLogger("dubbo.api");
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		RpcContext context = RpcContext.getContext();
		LOG.debug("reqeust start url:{},params:{}",context.getUrl().toFullString(),JsonUtil.toJson(context.getArguments()));
		
		//验证请求参数
		Object [] params  = context.getArguments();
		ValidateResult validate = null;
		for (Object object : params) {
			validate = ValidateUtils.validate(object);
			if (validate != null && validate.isHasErrors()) {
				break;
			}
		}
		
		// 执行请求
		Result result = null;
		if (validate == null ||(validate != null && !validate.isHasErrors())) {
			result = invoker.invoke(invocation);
		}
//		
//		// 验证错误  返回错误信息
//		if (validate.isHasErrors()) {
//			DubboApiResult<Object> temp = new DubboApiResult<Object>(ErrorCode.PARAMS_ERROR.getCode(), JsonUtil.toJson(validate.getErrorMsg().values()));
//			result  = new RpcResult(temp);
//		}
//		LOG.info("request end result:{}",JsonUtil.toJson(result.getValue()));
	return result;
	}

}
