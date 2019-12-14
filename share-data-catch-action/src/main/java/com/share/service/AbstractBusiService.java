package com.share.service;

import java.util.List;

import com.share.dto.BaseReqDto;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;
import com.share.user.IBusiService;

@SuppressWarnings("all")
public abstract class AbstractBusiService implements IBusiService{

	@Override
	public BaseRespDto<List<BaseUserInfo>> getUserInfoList(BaseReqDto<BaseUserInfo> dto) {
		return null;
	}

	@Override
	public BaseRespDto<BaseUserInfo> getUserInfoById(BaseReqDto<BaseUserInfo> dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseRespDto addBaseUser(BaseReqDto<BaseUserInfo> dto) {
		return null;
	}

	@Override
	public BaseRespDto updateBaseUser(BaseReqDto<BaseUserInfo> dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusiTypeEnum getBusiType() {
		return null;
	}
}
