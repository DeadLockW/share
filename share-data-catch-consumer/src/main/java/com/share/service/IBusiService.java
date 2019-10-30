package com.share.service;

import java.util.List;

import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;

public interface IBusiService {
	
	BaseRespDto<List<BaseUserInfo>> getUserInfoList(String param);
	
	BaseRespDto<BaseUserInfo> getUserInfoById(Long id);

	BaseRespDto addBaseUser(BaseUserInfo baseUserInfo);
	
	BaseRespDto updateBaseUser(BaseUserInfo baseUserInfo);
	
	BusiTypeEnum getBusiType();
}
