package com.share.service.impl.user.query;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.util.UuidUtils;
import com.share.constants.RabbitMqConstants;
import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;
import com.share.mq.RabbitSender;
import com.share.service.AbstractBusiService;
import com.share.service.impl.rpc.user.RpcQueryUserInfoService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@SuppressWarnings("all")
public class QueryUserInfoService extends AbstractBusiService {


	@Resource
	private RpcQueryUserInfoService rpcQueryUserInfoService;
	
	@Resource
	private RabbitSender rabbitSender;


	@Override
	public BaseRespDto<List<BaseUserInfo>> getUserInfoList(String param) {
		String json = null;
		try {
			json = rpcQueryUserInfoService.getUserInfoList(JSONObject.parseObject(param));
			 rabbitSender.send(RabbitMqConstants.TOPIC_EXCHANGE,RabbitMqConstants.ROUTINGKEY_QUERY_USER,param, UuidUtils.generateUuid());
			 log.info("===============QueryUserInfoService.getUserInfoList()发送日志记录消息成功==============");
			 log.info("===============QueryUserInfoService.getUserInfoList()获取用户列表数据成功==============");
			return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, JSONObject.parse(json));
		} catch (Exception e) {
			log.error("RpcQueryUserInfoService.getUserInfoList远程调用异常："+e);
			return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE, "远程调用失败");
		}
	}

	@Override
	public BaseRespDto getUserInfoById(String param) {

		String json = null;
		try {
			json =rpcQueryUserInfoService.getUserInfoById (JSONObject.parseObject(param));
			rabbitSender.send(RabbitMqConstants.TOPIC_EXCHANGE,RabbitMqConstants.ROUTINGKEY_QUERY_USER,param, UuidUtils.generateUuid());
			 log.info("===============QueryUserInfoService.getUserInfoById()发送日志记录消息成功==============");
			 log.info("===============QueryUserInfoService.getUserInfoById()根据id查询用户数据成功==============");
			return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, JSONObject.parse(json));
		} catch (Exception e) {
			log.error("RpcQueryUserInfoService.getUserInfoById远程调用异常："+e);
			return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE, "远程调用失败");
		}
	}

	@Override
	public BusiTypeEnum getBusiType() {
		return BusiTypeEnum.QUERY_SERVICE_1002;
	}

}
