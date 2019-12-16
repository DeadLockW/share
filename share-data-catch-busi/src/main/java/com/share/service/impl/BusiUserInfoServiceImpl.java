package com.share.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.share.RedisUtil;
import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.IBaseUserInfoService;
import com.share.service.IBusiUserInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * description: BusiUserInfoServiceImpl <br>
 * date: 2019/11/15 14:22 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
@Slf4j
public class BusiUserInfoServiceImpl implements IBusiUserInfoService {

    @Resource
    private IBaseUserInfoService iBaseUserInfoService;
    
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Boolean addBaseUser(BaseReqDto<BaseUserInfo> dto) {
    	try {
    		BaseUserInfo baseUserInfo = dto.getBodyObject(BaseUserInfo.class);
    		baseUserInfo.setCreateTime(new Date());
    		baseUserInfo.setUpdateTime(new Date());
    		iBaseUserInfoService.save(baseUserInfo);
    		log.info("==========BusiUserInfoServiceImpl.addBaseUser新增用户成功：{}",baseUserInfo.toString());
    		//新增用户,id做为key存入redis,过期时间100000毫秒
    		redisUtil.set(String.valueOf(baseUserInfo.getId()), JSONObject.toJSONString(baseUserInfo), 100000l);
    		log.info("==========BusiUserInfoServiceImpl.addBaseUser用户存入redis成功：{}",baseUserInfo.toString());
    		return true;
		} catch (Exception e) {
			return false;
		}
    }

    @Override
    public Boolean updateBaseUser(BaseReqDto<BaseUserInfo> dto) {
    	try {
    		BaseUserInfo baseUserInfo = dto.getBodyObject(BaseUserInfo.class);
    		baseUserInfo.setUpdateTime(new Date());
    		iBaseUserInfoService.updateById(baseUserInfo);
    		log.info("==========BusiUserInfoServiceImpl.addBaseUser更新用户成功：{}",baseUserInfo.toString());
    		//更新用户,id做为key存入redis,过期时间100000毫秒
    		redisUtil.set(String.valueOf(baseUserInfo.getId()), JSONObject.toJSONString(baseUserInfo), 100000l);
    		log.info("==========BusiUserInfoServiceImpl.addBaseUser更新用户成功：{}",baseUserInfo.toString());
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
}
