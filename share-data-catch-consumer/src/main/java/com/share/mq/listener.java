package com.share.mq;

import com.rabbitmq.client.Channel;
import com.share.constants.RabbitMqConstants;
import com.share.entity.BaseUserInfo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * description: Mq消息监听 <br>
 * date: 2019/11/7 15:54 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Component
public class listener {

    @RabbitListener(queues = RabbitMqConstants.QUEUE_ADD_USER)
    public void busiHandle(BaseUserInfo baseUserInfo, Message message, Channel channel) throws Exception{
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println(baseUserInfo.toString());
        System.out.println("============添加用户消息消费完成==================");
        channel.basicAck(deliveryTag,false);
    }

    @RabbitListener(queues = RabbitMqConstants.QUEUE_QUERY_USER)
    public void queryHandle(@RequestParam List list, Message message, Channel channel) throws Exception{
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println(list.toString());
        System.out.println("============查询用户消息消费完成==================");
        channel.basicAck(deliveryTag,false);
    }
}
