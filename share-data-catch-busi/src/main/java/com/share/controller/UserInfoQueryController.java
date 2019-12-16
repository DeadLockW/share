package com.share.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;
import com.share.service.IQueryUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

/**
 * description: UserInfoQueryControllrt <br>
 * date: 2019/11/13 14:18 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("queryUserInfo")
@RefreshScope
@Slf4j
public class UserInfoQueryController {

    @Resource
    private IQueryUserInfoService iQueryUserInfoService;

    @RequestMapping(value = "/getUserInfoById",method = RequestMethod.POST)
    public String getUserInfoById(@RequestBody BaseReqDto<BaseUserInfo> dto){
        log.info("==========根据id查询用户信息请求参数："+JSONObject.toJSONString(dto));
        BaseUserInfo  baseUserInfo = iQueryUserInfoService.getUserInfoById(dto);
        log.info("==========根据id查询用户信息返回参数："+JSONObject.toJSONString(baseUserInfo));
        return JSONObject.toJSONString(baseUserInfo);
    }

    @RequestMapping(value = "/getUserInfoList",method = RequestMethod.POST)
    public String getUserInfoList(@RequestBody BaseReqDto<BaseUserInfo> dto){
        log.info("==========查询用户信息列表请求参数："+JSONObject.toJSONString(dto));
        List<BaseUserInfo> list = iQueryUserInfoService.getUserInfoList(dto);
        log.info("==========查询用户信息列表返回参数："+JSONArray.toJSONString(list));
        return JSONObject.toJSONString(list);
    }
}
