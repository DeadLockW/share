package com.share.service.impl.query;

import java.util.List;

import javax.annotation.Resource;

import com.share.constants.RabbitMqConstants;
import com.share.mq.RabbitSender;
import com.share.service.impl.rpc.RpcQueryUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.AbstractBusiService;

@Component
@Slf4j
@SuppressWarnings("all")
public class QueryUserInfoService extends AbstractBusiService {


	@Resource
	private RpcQueryUserInfoService rpcQueryUserInfoService;

	@Override
	public BaseRespDto<List<BaseUserInfo>> getUserInfoList(String param) {

		String json = null;
		try {
			json = rpcQueryUserInfoService.getUserInfoList(JSONObject.parseObject(param));
			return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, JSONObject.parse(json));
		} catch (Exception e) {
			log.error("RpcQueryUserInfoService.getUserInfoList远程调用异常："+e);
			e.printStackTrace();
			return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE, ResultCodeConstants.HANDLE_FAIL_MSG);
		}
	}

	@Override
	public BaseRespDto getUserInfoById(String param) {

		String json = null;
		try {
			json =rpcQueryUserInfoService.getUserInfoById (JSONObject.parseObject(param));
			return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, JSONObject.parse(json));
		} catch (Exception e) {
			log.error("RpcQueryUserInfoService.getUserInfoById远程调用异常："+e);
			return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE, ResultCodeConstants.HANDLE_FAIL_MSG);
		}
	}

	@Override
	public BusiTypeEnum getBusiType() {
		return BusiTypeEnum.QUERY_SERVICE_1002;
	}

}
