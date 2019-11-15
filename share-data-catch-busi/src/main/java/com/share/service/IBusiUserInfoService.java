package com.share.service;

import com.alibaba.fastjson.JSONObject;

/**
 * description: IBusiUserInfoService <br>
 * date: 2019/11/15 14:21 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
public interface IBusiUserInfoService {

    /**
    * 添加用戶
    */
     Boolean addBaseUser(JSONObject json);

     /**
     * 編輯用戶
     */
     Boolean updateBaseUser(JSONObject json);
}
