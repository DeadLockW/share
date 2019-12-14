package com.share.controller.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.share.constants.ResultCodeConstants;
import com.share.controller.CommentController;
import com.share.dto.BaseReqDto;
import com.share.dto.BaseRespDto;
import com.share.enums.BusiTypeEnum;
import com.share.user.IBusiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("share")
@RefreshScope
@Api(description = "统一对外接口")
@Slf4j
@SuppressWarnings("all")
public class ShareInterfaceApiAction extends CommentController{
	

	@RequestMapping(value = "/api")
	@ApiOperation(value="统一請求API",httpMethod = "POST")
	public BaseRespDto api(@RequestBody String parm) {

        try {
        	if (StringUtils.isBlank(parm)) {
        		throw new IllegalArgumentException("请求参数不能为空！");
			}
            BaseReqDto dto = JSONObject.toJavaObject(JSONObject.parseObject(parm), BaseReqDto.class);
            paramValidate(dto);
            String busiType = dto.getHeader().getTransType();
            String actionName = dto.getHeader().getTransName();
            IBusiService service = beanOfServiceManager.getBusiService(BusiTypeEnum.fromCode(busiType));
           
            if (service == null) {
            	throw new IllegalArgumentException("业务模式不存在！");
            }
            return (BaseRespDto) iRouteExecutorService.execute(service,dto);
			//通过动态代理实现业务类
			//service = (IBusiService) invokeProxyService.newProxyInstance(queryUserInfoService);
        } catch (Exception e) {
        	log.error("系统异常："+e);
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,ResultCodeConstants.HANDLE_FAIL_MSG);
        }
	}
}
