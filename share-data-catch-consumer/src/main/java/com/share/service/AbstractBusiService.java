package com.share.service;

import java.util.List;

import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;

public abstract class AbstractBusiService implements IBusiService{

	@Override
	public BaseRespDto<List<BaseUserInfo>> getUserInfoList(String param) {
		return null;
	}

	@Override
	public BaseRespDto<BaseUserInfo> getUserInfoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseRespDto addBaseUser(BaseUserInfo baseUserInfo) {
		return null;
	}

	@Override
	public BaseRespDto updateBaseUser(BaseUserInfo baseUserInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusiTypeEnum getBusiType() {
		return null;
	}
}
