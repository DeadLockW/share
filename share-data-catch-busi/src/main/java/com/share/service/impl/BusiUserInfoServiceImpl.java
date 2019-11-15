package com.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.share.entity.BaseUserInfo;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.IBusiUserInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * description: BusiUserInfoServiceImpl <br>
 * date: 2019/11/15 14:22 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
public class BusiUserInfoServiceImpl implements IBusiUserInfoService {

    @Resource
    private BaseUserInfoMapper baseUserInfoMapper;

    @Override
    public Boolean addBaseUser(JSONObject json) {
        BaseUserInfo baseUserInfo = JSONObject.toJavaObject(json,BaseUserInfo.class);
        int count = baseUserInfoMapper.insert(baseUserInfo);
        return count == 1? true:false;
    }

    @Override
    public Boolean updateBaseUser(JSONObject json) {
        BaseUserInfo baseUserInfo = JSONObject.toJavaObject(json,BaseUserInfo.class);
        int count = baseUserInfoMapper.updateById(baseUserInfo);
        return count == 1? true:false;
    }
}
