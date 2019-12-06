package com.share.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.IBusiUserInfoService;

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
    public Boolean addBaseUser(BaseReqDto<BaseUserInfo> dto) {
    	BaseUserInfo baseUserInfo = dto.getBodyObject(BaseUserInfo.class);
    	baseUserInfo.setCreateTime(new Date());
    	baseUserInfo.setUpdateTime(new Date());
        int count = baseUserInfoMapper.insert(baseUserInfo);
        return count == 1? true:false;
    }

    @Override
    public Boolean updateBaseUser(BaseReqDto<BaseUserInfo> dto) {
    	BaseUserInfo baseUserInfo = dto.getBodyObject(BaseUserInfo.class);
    	baseUserInfo.setUpdateTime(new Date());
        int count = baseUserInfoMapper.updateById(baseUserInfo);
        return count == 1? true:false;
    }
}
