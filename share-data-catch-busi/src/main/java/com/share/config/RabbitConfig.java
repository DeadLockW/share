package com.share.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.share.constants.RabbitMqConstants;

/**
 * rabbitMq 配置类(主要为了定制一些处理策略，如生产端手动确认机制)
 * @author wk
 * Created on 2018/9/14
 */
@Configuration
public class RabbitConfig {
	
	 //订阅模式
    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE,true,false);
    }
	
    @Bean("saveLogQueue")
    public Queue queryLogQueue(){
        return new Queue(RabbitMqConstants.QUEUE_SAVE_LOG,true);
    }
    
    @Bean("saveLogBinding")
    Binding saveLogBinding(@Qualifier("saveLogQueue")Queue queue,@Qualifier("topicExchange") TopicExchange topicExchange){
    	return BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstants.ROUTINGKEY_LOG_SEND);
    }
    
}
