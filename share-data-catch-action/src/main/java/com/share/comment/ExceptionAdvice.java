package com.share.comment;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.share.constants.ResultCodeConstants;
import com.share.dto.BaseRespDto;

import lombok.extern.slf4j.Slf4j;

/**
 * description: 统一异常处理 <br>
 * date: 2019/11/12 14:59 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@ControllerAdvice
@EnableAspectJAutoProxy
@Slf4j
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseRespDto defaultExceptionHandler (Exception exception) {
        try {
            log.warn("全局业务处理异常 >> error = {}", exception.getMessage(), exception);
            throw exception;
        } catch (IllegalArgumentException e){
            log.error("IllegalArgumentException异常");
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,e.getMessage());
        } catch (JSONException e) {
            log.error("JSONException异常");
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,e.getMessage());
        } catch (Exception e) {
            log.error("Exception异常");
            return BaseRespDto.build(ResultCodeConstants.HANDLE_FAIL_CODE,e.getMessage());
        }
    }
}
