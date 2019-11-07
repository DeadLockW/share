package com.share.service.impl.query;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseRespDto;
import com.share.entity.BaseUserInfo;
import com.share.enums.BusiTypeEnum;
import com.share.mapper.BaseUserInfoMapper;
import com.share.service.AbstractBusiService;

@Component
@SuppressWarnings("all")
public class QueryUserInfoService extends AbstractBusiService {

	@Resource
	private BaseUserInfoMapper baseUserInfoMapper;
	
	@Override
	public BaseRespDto<List<BaseUserInfo>> getUserInfoList(String param) {
		JSONObject json = JSONObject.parseObject(param);
		String userName = json.getString("userName");
		String phone = json.getString("phone");
		String sex = json.getString("sex");
		String professional = json.getString("professional");
		QueryWrapper<BaseUserInfo> queryWrapper = new QueryWrapper<BaseUserInfo>();
		if (StringUtils.isNotBlank(userName)) {
			queryWrapper.like("user_real_name", userName);
		}
		if (StringUtils.isNotBlank(phone)) {
			queryWrapper.eq("phone_num", phone);
		}
		if (StringUtils.isNotBlank(sex)) {
			queryWrapper.eq("user_sex", sex);
		}
		if (StringUtils.isNotBlank(professional)) {
			queryWrapper.like("professional", professional);
		}
		List<BaseUserInfo> list = baseUserInfoMapper.selectList(queryWrapper);
		return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, JSONObject.toJSON(list));
	}

	@Override
	public BaseRespDto getUserInfoById(String param) {
		JSONObject json = JSONObject.parseObject(param);
		Long id = json.getLong("id");
		BaseUserInfo baseUserInfo = baseUserInfoMapper.selectById(id);
		return BaseRespDto.build(ResultCodeConstants.HANDLE_SUCCESS_CODE, ResultCodeConstants.HANDLE_SUCCESS_MSG, JSONObject.toJSON(baseUserInfo));
	}

	@Override
	public BusiTypeEnum getBusiType() {
		return BusiTypeEnum.QUERY_SERVICE_1002;
	}

}
