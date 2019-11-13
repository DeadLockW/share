package com.share.comment;

import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseRespDto;
import com.share.service.IBusiService;
import com.share.service.IRouteExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
    public Object execute(Object obj, String actionName, String param) {

        Method method = null;
        try {
            method = obj.getClass().getMethod(actionName,String.class);
            if (method == null) throw new IllegalArgumentException("方法不存在");
            return method.invoke(obj,param);
        } catch (NoSuchMethodException e) {
            log.error("方法不存在："+e);
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,"方法不存在");
        } catch (Exception e) {
            log.error("路由器执行失败："+e);
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,"路由器执行失败");
        }
    }
}
