package com.share.mq;

import javax.annotation.Resource;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.GetResponse;
import com.share.constants.MqStatusContants;
import com.share.service.componet.ISaveMqLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * description: RabbitSender <br>
 * date: 2019/11/7 15:44 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
@Slf4j
public class RabbitSender {

    @Resource
    private RabbitTemplate rabbitTemplate;
    
    @Resource
    private ISaveMqLogService iSaveMqLogService;
    
    @Resource
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    /** 
    * @Description: 发送消息
    * @Param:  exchange 交换器  routKey 路由  msg 消息对象 msgId 消息id
    * @Author: wk
    * @Date: 2019/11/7 
    */
    public void send (String exchange, String routKey ,Object msg ,String msgId) {

        try {
        	rabbitTemplate.setConfirmCallback(confirmCallback);
        	rabbitTemplate.setReturnCallback(returnCallback);
        	rabbitTemplate.setMandatory(true);
        	rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
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
    
    
    /**
     *  使用该功能需要开启确认，spring-boot中配置如下：
  	 *	spring.rabbitmq.publisher-confirms = true
  	 *	异步监听确认消息是否发送到交换机
     */
    final RabbitTemplate.ConfirmCallback confirmCallback= new RabbitTemplate.ConfirmCallback() {

        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            if(!ack){
            	log.info("==========消息发送服务器异常，失败原因："+cause);
            } else {
            	log.info("==========消息发送服务器成功！");
			}
            //更新mq状态
            iSaveMqLogService.updateMqLog(ack ? MqStatusContants.MQ_STATUS_0 : MqStatusContants.MQ_STATUS_3, correlationData.getId());
        }

    };
    /**
     *  使用该功能需要开启确认，spring-boot中配置如下：
  	 *	spring.rabbitmq.publisher-returns = true
  	 *	如果消息从交换器发送到对应队列失败时触发
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {

        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            log.info("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };
}
