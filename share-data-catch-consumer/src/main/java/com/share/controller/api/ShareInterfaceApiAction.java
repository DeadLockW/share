package com.share.controller.api;

import java.util.UUID;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.share.constants.RabbitMqConstants;
import com.share.constants.ResultCodeConstants;
import com.share.controller.CommentController;
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

        JSONObject json = null;
        try {
            json = paramValidate(parm);
            String busiType = json.getString("busiType");
            String actionName = json.getString("actionName");
            IBusiService service = beanOfServiceManager.getBusiService(BusiTypeEnum.fromCode(busiType));
            if (service == null) throw new IllegalArgumentException("业务模式不存在！");

            //mq通配符路由模式测试
            rabbitSender.send(RabbitMqConstants.TOPIC_EXCHANGE, RabbitMqConstants.ROUTINGKEY_LOG_SEND, "日志输出", UUID.randomUUID().toString());
            
            return (BaseRespDto) iRouteExecutorService.execute(service,actionName,parm);
//		通过动态代理实现业务类
//		service = (IBusiService) invokeProxyService.newProxyInstance(queryUserInfoService);
        } catch (Exception e) {
        	log.error("系统异常："+e);
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,ResultCodeConstants.HANDLE_FAIL_MSG);
        }
	}
}