package com.share.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.share.constants.RabbitMqConstants;

/**
 * rabbitMq 配置类(路由通配符模式。记录用户相关的操作日志)
 * @author wk
 * Created on 2018/9/14
 */
@Configuration
public class RabbitConfig {
	
	 //路由通配符模式
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
