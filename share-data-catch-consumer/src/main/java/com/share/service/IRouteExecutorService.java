package com.share.service;

import com.share.dto.BaseRespDto;

/**
 * description: IRouteExecutorService <br>
 * date: 2019/11/12 14:30 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
public interface IRouteExecutorService {

    /**
    * 路由执行器
    */
    public Object execute(Object obj, String actionName, String param);
}
