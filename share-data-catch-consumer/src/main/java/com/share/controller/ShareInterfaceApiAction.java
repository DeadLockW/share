package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.share.comment.BankServiceManager;
import com.share.dto.BaseRespDto;
import com.share.enums.ActionTypeEnum;
import com.share.enums.BusiTypeEnum;
import com.share.service.IBusiService;
import com.share.service.IRouteExecutorService;
import com.share.service.impl.busi.BusiUserInfoService;
import com.share.service.impl.proxy.InvokeProxyService;
import com.share.service.impl.query.QueryUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
@RequestMapping("share")
@RefreshScope
@Api(description = "统一对外接口")
@SuppressWarnings("all")
public class ShareInterfaceApiAction extends CommentController{

	@RequestMapping(value = "/api")
	@ApiOperation(value="统一請求API",httpMethod = "POST")
	public BaseRespDto api(@RequestBody String parm) throws Exception {

		JSONObject json = paramValidate(parm);
		String busiType = json.getString("busiType");
		String actionName = json.getString("actionName");
		IBusiService service = bankServiceManager.getBusiService(BusiTypeEnum.fromCode(busiType));
		if (service == null) throw new IllegalArgumentException("业务模式不存在！");
		return (BaseRespDto) iRouteExecutorService.execute(service,actionName,parm);
//		通过动态代理实现业务类
//		service = (IBusiService) invokeProxyService.newProxyInstance(queryUserInfoService);
	}
}
