package com.share.mq;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.share.constants.RabbitMqConstants;
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
public class listener {

    @Resource
    private IBusiUserInfoService iBusiUserInfoService;

    @RabbitListener(queues = RabbitMqConstants.QUEUE_ADD_USER)
    public void addBusiHandle(JSONObject json, Message message, Channel channel) throws Exception{
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();


        try {
            iBusiUserInfoService.addBaseUser(json);
            log.info("===============listener.addBusiHandle()消费新增用户消息成功==============");
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = RabbitMqConstants.QUEUE_UPDATE_USER)
    public void updateBusiHandle(@RequestParam JSONObject json, Message message, Channel channel) throws Exception{
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            iBusiUserInfoService.updateBaseUser(json);
            log.info("===============listener.updateBusiHandle()消费更新用户消息成功==============");
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            e.printStackTrace();
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
    public void queryLogBusiHandle(@RequestParam String str, Message message, Channel channel) throws Exception{ final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
        	log.info("===============listener.queryLogBusiHandle()消费记录日志消息成功："+str);
            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
