package com.share.service;

import java.util.List;

import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;

public interface IBusiService {
	
	BaseRespDto<List<BaseUserInfo>> getUserInfoList(String param);
	
	BaseRespDto<BaseUserInfo> getUserInfoById(String param);

	BaseRespDto addBaseUser(String param);
	
	BaseRespDto updateBaseUser(String param);
	
	BusiTypeEnum getBusiType();
}
