package com.share.config;

import com.share.constants.RabbitMqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: mq配置 direct模式
 * date: 2019/11/7 15:29 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Configuration
public class RabbitMqConfig {

    /************************绑定业务查询队列和路由*******************************/

    @Bean("queryQueue")
    public Queue queryQueue(){
        return new Queue(RabbitMqConstants.QUEUE_QUERY_USER);
    }

    @Bean("queryDirectExchange")
    public DirectExchange queryDirectExchange(){
        return new DirectExchange(RabbitMqConstants.QUERY_EXCHANGE);
    }

    @Bean("queryBinding")
    Binding queryBinding(@Qualifier("queryQueue") Queue queue, @Qualifier("queryDirectExchange") DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(RabbitMqConstants.ROUTINGKEY_QUERY_USER);
    }

    /************************绑定业务查询队列和路由*******************************/

    @Bean("busiQueue")
    public Queue busiQueue(){
        return new Queue(RabbitMqConstants.QUEUE_ADD_USER);
    }

    @Bean("busiDirectExchange")
    public DirectExchange busiDirectExchange(){
        return new DirectExchange(RabbitMqConstants.BUSI_EXCHANGE);
    }

    @Bean("busiBinding")
    Binding busiBinding(@Qualifier("busiQueue") Queue queue,@Qualifier("busiDirectExchange") DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(RabbitMqConstants.ROUTINGKEY_ADD_USER);
    }
}
