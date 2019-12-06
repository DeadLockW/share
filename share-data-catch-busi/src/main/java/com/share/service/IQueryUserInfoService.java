package com.share.service;

import java.util.List;

import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;

/**
 * description: IQueryUserInfoService <br>
 * date: 2019/11/13 16:15 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
public interface IQueryUserInfoService {

    public List<BaseUserInfo>  getUserInfoList(BaseReqDto<BaseUserInfo> dto);

    public BaseUserInfo getUserInfoById(BaseReqDto<BaseUserInfo> dto);
}
