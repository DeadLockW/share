package com.share.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.RedisUtil;
import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.IQueryUserInfoService;

import lombok.extern.slf4j.Slf4j;

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
    
    @Resource
    private RedisUtil redisUil;

    @Override
    public List<BaseUserInfo> getUserInfoList(BaseReqDto<BaseUserInfo> dto) {
    	BaseUserInfo baseUserInfo = dto.getBodyObject(BaseUserInfo.class);
        QueryWrapper<BaseUserInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(baseUserInfo.getUserRealName())) {
        	queryWrapper.eq("user_real_name",baseUserInfo.getUserRealName());
		}
        if (StringUtils.isNotBlank(baseUserInfo.getPhoneNum())) {
        	queryWrapper.eq("phone_num",baseUserInfo.getPhoneNum());
		}
        if (StringUtils.isNotBlank(baseUserInfo.getNickName())) {
        	queryWrapper.like("nick_name",baseUserInfo.getNickName());
		}
        if (StringUtils.isNotBlank(baseUserInfo.getProfessional())) {
        	queryWrapper.like("professional",baseUserInfo.getProfessional());
		}
        List<BaseUserInfo> resultList = baseUserInfoMapper.selectList(queryWrapper);
        return resultList;
    }
    @Override
    public BaseUserInfo getUserInfoById(BaseReqDto<BaseUserInfo> dto) {
    	BaseUserInfo baseUserInfo = dto.getBodyObject(BaseUserInfo.class);
    	
    	//从redis获取值，如果不为空直接返回，为空的话查询数据库
    	String vaule = redisUil.get(String.valueOf(baseUserInfo.getId()));
    	if (StringUtils.isNotBlank(vaule)) {
    		BaseUserInfo result = JSONObject.toJavaObject(JSONObject.parseObject(vaule), BaseUserInfo.class);
    		log.info("==========id:{}从redis中获取值",baseUserInfo.getId());
    		return result;
		} else {
			log.info("==========id:{}从数据库中获取值：{}",baseUserInfo.getId());
			return baseUserInfoMapper.selectById(baseUserInfo.getId());
		}
    }
}
