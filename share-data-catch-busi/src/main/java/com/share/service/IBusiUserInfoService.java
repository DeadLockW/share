package com.share.service;

import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;

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
     Boolean addBaseUser(BaseReqDto<BaseUserInfo> dto);

     /**
     * 編輯用戶
     */
     Boolean updateBaseUser(BaseReqDto<BaseUserInfo> dto);
}
