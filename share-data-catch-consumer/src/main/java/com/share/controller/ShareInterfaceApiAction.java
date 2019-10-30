package com.share.controller;

import javax.annotation.Resource;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.share.comment.BankServiceManager;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.ActionTypeEnum;
import com.share.enums.BusiTypeEnum;
import com.share.service.IBusiService;
import com.share.service.impl.busi.BusiUserInfoService;
import com.share.service.impl.query.QueryUserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("share")
@RefreshScope
@Api(description = "用户模块")
@SuppressWarnings("all")
public class ShareInterfaceApiAction {
	
	@Resource
	private QueryUserInfoService queryUserInfoService;
	
	@Resource
	private BusiUserInfoService busiUserInfoService;
	
	@Resource
	private BankServiceManager bankServiceManager;
	
	@RequestMapping(value = "/api")
	@ApiOperation(value="统一对外接口",httpMethod = "POST")
	public BaseRespDto api(@RequestBody String parm) {
		JSONObject json = JSONObject.parseObject(parm);
		String busiType = json.getString("busiType");
		String actionName = json.getString("actionName");
		IBusiService service = bankServiceManager.getBusiService(BusiTypeEnum.fromCode(busiType));
		
		//通过动态代理实现业务类
		//InvokeProxyService proxyService = new InvokeProxyService();
		//service = (IBusiService) proxyService.newProxyInstance(queryUserInfoService);
		
		BaseRespDto baseRespDto = null;
		ActionTypeEnum actionTypeEnum = ActionTypeEnum.fromCode(actionName);
		
		switch (actionTypeEnum) {
		case GET_USER_INFO_LIST:
			baseRespDto = service.getUserInfoList(parm);
			break;
		case ADD_USER_INFO:
			baseRespDto = service.addBaseUser(new BaseUserInfo());
			break;
		default:
			break;
		}
		return baseRespDto;
	}

}
