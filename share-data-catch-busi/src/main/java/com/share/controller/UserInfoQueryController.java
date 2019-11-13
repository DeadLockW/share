package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.share.service.IQueryUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String getUserInfoById(@RequestBody JSONObject json){
        log.info("======================UserInfoQueryController.getUserInfoById");
        log.info("======================请求参数："+json.toString());
        return JSONObject.toJSONString(iQueryUserInfoService.getUserInfoById(json));
    }

    @RequestMapping(value = "/getUserInfoList",method = RequestMethod.POST)
    public String getUserInfoList(@RequestBody JSONObject json){
        log.info("======================UserInfoQueryController.getUserInfoList");
        log.info("======================请求参数："+json.toString());
        return JSONObject.toJSONString(iQueryUserInfoService.getUserInfoList(json));
    }
}
