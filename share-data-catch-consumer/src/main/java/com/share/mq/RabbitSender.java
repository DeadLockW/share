package com.share.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.GetResponse;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * description: RabbitSender <br>
 * date: 2019/11/7 15:44 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
public class RabbitSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /** 
    * @Description: 发送消息
    * @Param:  exchange 交换器  routKey 路由  msg 消息对象 msgId 消息id
    * @Author: wk
    * @Date: 2019/11/7 
    */
    public void send (String exchange, String routKey ,Object msg ,String msgId) {

        try {
            rabbitTemplate.convertAndSend(exchange, routKey,
                    msg, new CorrelationData(msgId));
        } catch (AmqpException e) {
            throw e;
        }
    }

    /**
     * 手动从 MQ 拉取消息
     * @param queue 队列名
     * @param clazz 返回类型
     * @param <T>
     * @return
     */
    public <T> T pullMessage(String queue, Class<T> clazz) {
        return rabbitTemplate.execute(channel -> {
            GetResponse response = channel.basicGet(queue, false);
            if (response == null) {
                return null;
            }
            long deliveryTag = response.getEnvelope().getDeliveryTag();

            try {
                ObjectMapper mapper = new ObjectMapper();
                T t = mapper.readValue(response.getBody(), clazz);

                channel.basicAck(deliveryTag, false);
                return t;
            } catch (Exception e) {
                channel.basicReject(deliveryTag, true);
                throw e;
            }
        });
    }
}
