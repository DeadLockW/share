package com.share.user;

import java.util.List;

import com.share.dto.BaseReqDto;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;

public interface IBusiService {
	
	BaseRespDto<List<BaseUserInfo>> getUserInfoList(BaseReqDto<BaseUserInfo> dto);
	
	BaseRespDto<BaseUserInfo> getUserInfoById(BaseReqDto<BaseUserInfo> dto);

	BaseRespDto addBaseUser(BaseReqDto<BaseUserInfo> dto);
	
	BaseRespDto updateBaseUser(BaseReqDto<BaseUserInfo> dto);
	
	BusiTypeEnum getBusiType();
	
}
