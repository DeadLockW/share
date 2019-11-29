package com.share.service.impl.rpc.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * description: RpcQueryUserInfoService <br>
 * date: 2019/11/13 14:29 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@FeignClient(name = "share-data-catch-busi")
public interface RpcQueryUserInfoService {

    @RequestMapping(value = "/queryUserInfo/getUserInfoById",method = RequestMethod.POST)
    String getUserInfoById(@RequestBody JSONObject json);

    @RequestMapping(value = "/queryUserInfo/getUserInfoList",method = RequestMethod.POST)
    String getUserInfoList(@RequestBody JSONObject json);
}
