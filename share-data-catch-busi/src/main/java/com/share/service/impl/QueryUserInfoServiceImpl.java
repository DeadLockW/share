package com.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.BaseUserInfo;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.IQueryUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * description: QueryUserInfoServiceImpl <br>
 * date: 2019/11/13 16:17 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
@Slf4j
public class QueryUserInfoServiceImpl implements IQueryUserInfoService {

    @Resource
    private BaseUserInfoMapper baseUserInfoMapper;

    @Override
    public List<BaseUserInfo> getUserInfoList(JSONObject json) {
        QueryWrapper<BaseUserInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(json.getString("userRealName"))) queryWrapper.eq("user_real_name",json.getString("userRealName"));
        if (StringUtils.isNotBlank(json.getString("phoneNum"))) queryWrapper.eq("phone_num",json.getString("userRealName"));
        if (StringUtils.isNotBlank(json.getString("nickName"))) queryWrapper.like("nick_name",json.getString("nickName"));
        if (StringUtils.isNotBlank(json.getString("professional"))) queryWrapper.like("professional",json.getString("professional"));
        return baseUserInfoMapper.selectList(queryWrapper);
    }
    @Override
    public BaseUserInfo getUserInfoById(JSONObject json) {
       return baseUserInfoMapper.selectById(json.getLong("id"));
    }
}
