package com.share.comment;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseReqDto;
import com.share.dto.BaseRespDto;

import lombok.extern.slf4j.Slf4j;

/**
 * description: 路由控制类 <br>
 * date: 2019/11/12 14:27 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
@Slf4j
public class RouteExecutorService implements IRouteExecutorService {

    @Override
    public Object execute(Object obj, @SuppressWarnings("rawtypes") BaseReqDto dto) {

        try {
        	if(obj == null) {
        		throw new IllegalArgumentException("找不到对应的业务类型");
        	}
        	String actionName = dto.getHeader().getTransName();
        	if (StringUtils.isBlank(actionName)) {
        		throw new IllegalArgumentException("业务名称不能为空");
			}
        	log.info("RouteExecutorService.execute执行器开始执行，业务类名称："+obj.getClass().getName());
        	log.info("RouteExecutorService.execute执行器开始执行，函数名称："+actionName);
        	log.info("RouteExecutorService.execute执行器开始执行，请求参数："+dto.toString());
        	
            Method method = obj.getClass().getMethod(actionName,BaseReqDto.class);
            if (method == null) {
            	throw new IllegalArgumentException("不存在该函数");
            }
            return method.invoke(obj,dto);
        } catch (NoSuchMethodException e) {
            log.error("不存在该函数："+e);
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,"不存在该函数");
        } catch (Exception e) {
            log.error("路由器执行失败："+e);
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,"路由器执行失败");
        }
    }
}
