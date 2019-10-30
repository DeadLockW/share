package com.share.service.impl.busi;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.AbstractBusiService;

@Component
@SuppressWarnings("all")
public class BusiUserInfoService extends AbstractBusiService {

	@Resource
	private BaseUserInfoMapper baseUserInfoMapper;
	
	@Override
	public BaseRespDto addBaseUser(BaseUserInfo baseUserInfo) {
		baseUserInfoMapper.insert(baseUserInfo);
		return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, null);
	}

	@Override
	public BaseRespDto updateBaseUser(BaseUserInfo baseUserInfo) {
		//采用mq异步插入的形式
		baseUserInfoMapper.updateById(baseUserInfo);
		return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, null);
	}

	@Override
	public BusiTypeEnum getBusiType() {
		return BusiTypeEnum.BUSI_SERVICE_1001;
	}

}
