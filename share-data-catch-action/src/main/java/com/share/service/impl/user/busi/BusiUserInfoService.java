package com.share.service.impl.user.busi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.share.constants.RabbitMqConstants;
import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseReqDto;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;
import com.share.mapper.BaseUserInfoMapper;
import com.share.mq.RabbitSender;
import com.share.service.AbstractBusiService;
import com.share.service.componet.ISaveMqLogService;

import lombok.extern.slf4j.Slf4j;

@Component
@SuppressWarnings("all")
@Slf4j
public class BusiUserInfoService extends AbstractBusiService {

	@Resource
	private BaseUserInfoMapper baseUserInfoMapper;

	@Resource
	private RabbitSender rabbitSender;
	
	@Resource
	private ISaveMqLogService iSaveMqLogService;
	
	
	@Override
	public BaseRespDto addBaseUser(BaseReqDto<BaseUserInfo> dto) {
		//采用mq异步插入的形式
        try {
           
            rabbitSender.send(RabbitMqConstants.BUSI_EXCHANGE,RabbitMqConstants.ROUTINGKEY_ADD_USER,JSONObject.toJSONString(dto), dto.getHeader().getTransId());
            log.info("==========BusiUserInfoService.addBaseUser()发送新增用户消息成功：{}",JSONObject.toJSONString(dto));
            log.info("==========BusiUserInfoService.addBaseUser()发送日志记录消息成功");
            
			iSaveMqLogService.saveMqLog(dto.getHeader().getTransType(), dto.getHeader().getTransName(), dto.getHeader().getTransId(), JSONObject.toJSONString(dto));
            return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE, "发送MQ失败");
        }
	}

	@Override
	public BaseRespDto updateBaseUser(BaseReqDto<BaseUserInfo> dto) {

        try {
            rabbitSender.send(RabbitMqConstants.BUSI_EXCHANGE,RabbitMqConstants.ROUTINGKEY_UPDATE_USER,JSONObject.toJSONString(dto), dto.getHeader().getTransId());
            log.info("===============BusiUserInfoService.updateBaseUser()发送更新用户信息消息成功==============");
            log.info("===============BusiUserInfoService.updateBaseUser()发送日志记录消息成功==============");
            
            iSaveMqLogService.saveMqLog(dto.getHeader().getTransType(), dto.getHeader().getTransName(), dto.getHeader().getTransId(), JSONObject.toJSONString(dto));
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
