package com.share.comment;

import com.share.dto.BaseReqDto;

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
    public Object execute(Object obj, BaseReqDto dto);
}
