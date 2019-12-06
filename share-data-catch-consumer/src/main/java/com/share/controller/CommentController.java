package com.share.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.alibaba.fastjson.JSONObject;
import com.share.comment.BeanOfServiceManager;
import com.share.comment.IRouteExecutorService;
import com.share.dto.BaseReqDto;
import com.share.mq.RabbitSender;

/**
 * description: CommentController <br>
 * date: 2019/11/13 11:02 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Controller
@CrossOrigin//解决跨域问题
public class CommentController {

//    @Resource
//    protected QueryUserInfoService queryUserInfoService;
//
//    @Resource
//    protected BusiUserInfoService busiUserInfoService;

    @Resource
    protected BeanOfServiceManager beanOfServiceManager;

    @Resource
    protected IRouteExecutorService iRouteExecutorService;
    
    @Resource
    protected RabbitSender rabbitSender;

//    @Resource
//    protected InvokeProxyService invokeProxyService;

    /**
    * 請求參數校驗
    */
    public void paramValidate(BaseReqDto dto) {
        try {
            String busiType = dto.getHeader().getTransType();
            if (StringUtils.isBlank(busiType))throw new IllegalArgumentException("业务模式不能为空！");
            String actionName = dto.getHeader().getTransName();
            if (StringUtils.isBlank(actionName)) throw new IllegalArgumentException("函数名不能为空！");
        } catch (Exception e){
            throw new IllegalArgumentException("请求参数格式有误！");
        }
    }
}
