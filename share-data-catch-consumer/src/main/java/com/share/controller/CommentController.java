package com.share.controller;

import com.alibaba.fastjson.JSONObject;
import com.share.comment.BeanOfServiceManager;
import com.share.service.IRouteExecutorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;

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

//    @Resource
//    protected InvokeProxyService invokeProxyService;

    /**
    * 請求參數校驗
    */
    public JSONObject paramValidate(String param) {
        try {
            JSONObject json = JSONObject.parseObject(param);
            String busiType = json.getString("busiType");
            if (StringUtils.isBlank(busiType))throw new IllegalArgumentException("业务模式不能为空！");
            String actionName = json.getString("actionName");
            if (StringUtils.isBlank(actionName)) throw new IllegalArgumentException("函数名不能为空！");
            return json;
        } catch (Exception e){
            throw new IllegalArgumentException("请求参数格式有误！");
        }
    }
}
