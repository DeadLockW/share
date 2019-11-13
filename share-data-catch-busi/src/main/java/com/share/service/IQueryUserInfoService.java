package com.share.service;

import com.alibaba.fastjson.JSONObject;
import com.share.entity.BaseUserInfo;

import java.util.List;

/**
 * description: IQueryUserInfoService <br>
 * date: 2019/11/13 16:15 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
public interface IQueryUserInfoService {

    public List<BaseUserInfo>  getUserInfoList(JSONObject json);

    public BaseUserInfo getUserInfoById(JSONObject json);
}
