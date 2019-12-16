package com.share.mq;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.share.constants.RabbitMqConstants;
import com.share.dto.BaseReqDto;
import com.share.entity.BaseUserInfo;
import com.share.service.IBusiUserInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * description: Mq消息监听 <br>
 * date: 2019/11/7 15:54 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
@Slf4j
@SuppressWarnings("all")
public class listener {

    @Resource
    private IBusiUserInfoService iBusiUserInfoService;

    @RabbitListener(queues = RabbitMqConstants.QUEUE_ADD_USER)
    public void addBusiHandle(@RequestParam String msg, Message message, Channel channel) throws Exception{
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
			BaseReqDto<BaseUserInfo> dto = JSONObject.toJavaObject(JSONObject.parseObject(msg), BaseReqDto.class);
            iBusiUserInfoService.addBaseUser(dto);
            log.info("==========listener.addBusiHandle()消费新增用户消息成功");
        } catch (Exception e) {
        	log.error("新增用户异常："+e);
        } finally {
        	channel.basicAck(deliveryTag, false);//确认消费，从队列中删除消息   true：不删除
//        	channel.basicReject(deliveryTag,true);//如果拒绝消息，且为true:消息会重新入队列，并且不断消费，如果false拒绝消息，且从队列中删除消息
		}
    }

    @RabbitListener(queues = RabbitMqConstants.QUEUE_UPDATE_USER)
    public void updateBusiHandle(@RequestParam String msg, Message message, Channel channel) throws Exception{
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
        	BaseReqDto<BaseUserInfo> dto = JSONObject.toJavaObject(JSONObject.parseObject(msg), BaseReqDto.class);
            iBusiUserInfoService.updateBaseUser(dto);
            log.info("==========listener.updateBusiHandle()消费更新用户消息成功");
        } catch (Exception e) {
        	log.error("新增用户异常："+e);
        } finally {
        	channel.basicAck(deliveryTag,false);
		}
    }
    
    
    /**
     * 	记录日志监听（通配符模式）
     * @param str
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = RabbitMqConstants.QUEUE_SAVE_LOG)
    public void saveLogHandle(@RequestParam String msg, Message message, Channel channel) throws Exception{ final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
        	log.info("==========listener.queryLogBusiHandle()消费记录日志消息成功："+msg);
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
        	channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
    
}
