package com.share.service.impl.busi;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.share.constants.RabbitMqConstants;
import com.share.mq.RabbitSender;
import org.springframework.stereotype.Component;

import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.AbstractBusiService;

import java.util.UUID;

@Component
@SuppressWarnings("all")
public class BusiUserInfoService extends AbstractBusiService {

	@Resource
	private BaseUserInfoMapper baseUserInfoMapper;

	@Resource
	private RabbitSender rabbitSender;
	
	@Override
	public BaseRespDto addBaseUser(String param) {
		//采用mq异步插入的形式
        BaseUserInfo baseUserInfo = null;
        try {
            baseUserInfo = JSONObject.toJavaObject(JSONObject.parseObject(param), BaseUserInfo.class);
            rabbitSender.send(RabbitMqConstants.BUSI_EXCHANGE,RabbitMqConstants.ROUTINGKEY_ADD_USER,baseUserInfo, baseUserInfo.getUserId());
            return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE, "发送MQ失败");
        }
	}

	@Override
	public BaseRespDto updateBaseUser(String param) {

        BaseUserInfo baseUserInfo = null;
        try {
            baseUserInfo = JSONObject.toJavaObject(JSONObject.parseObject(param), BaseUserInfo.class);
            rabbitSender.send(RabbitMqConstants.BUSI_EXCHANGE,RabbitMqConstants.ROUTINGKEY_UPDATE_USER,baseUserInfo, baseUserInfo.getUserId());
            return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE, "发送MQ失败");
        }
	}

	@Override
	public BusiTypeEnum getBusiType() {
		return BusiTypeEnum.BUSI_SERVICE_1001;
	}
}
