package com.share.service.impl.rpc.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;

/**
 * description: fegin远程调用 <br>
 * date: 2019/11/13 14:29 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@FeignClient(name = FeginQueryUserInfoService.APPICATION_NAME)
public interface FeginQueryUserInfoService {
	
    public static final String APPICATION_NAME = "share-data-catch-busi";
    
    public static final String GET_USER_INFO_BY_ID_URL = "/queryUserInfo/getUserInfoById";
    
    public static final String GET_USER_INFO_LIST = "/queryUserInfo/getUserInfoList";

    
    @RequestMapping(value = FeginQueryUserInfoService.GET_USER_INFO_BY_ID_URL,method = RequestMethod.POST)
    String getUserInfoById(@RequestBody BaseReqDto<BaseUserInfo> dto);

    @RequestMapping(value = FeginQueryUserInfoService.GET_USER_INFO_LIST,method = RequestMethod.POST)
    String getUserInfoList(@RequestBody BaseReqDto<BaseUserInfo> dto);
}
