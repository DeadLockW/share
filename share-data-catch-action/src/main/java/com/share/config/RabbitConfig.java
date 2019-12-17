package com.share.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.share.constants.RabbitMqConstants;

/**
 * rabbitMq 配置类
 * @author wk
 * Created on 2018/9/14
 */
@Configuration
public class RabbitConfig {
	
	 /**
     * Queue和交换机 可以有4个参数
     *      1.队列名
     *      2.durable       持久化消息队列 ,rabbitmq重启的时候不需要创建新的队列 默认true
     *      3.auto-delete   表示消息队列没有在使用时将被自动删除 默认是false
     *      4.exclusive     表示该消息队列是否只在当前connection生效,默认是false
     */
	
	//路由模式
	@Bean("busiDirectExchange")
    public DirectExchange busiDirectExchange(){
        return new DirectExchange(RabbitMqConstants.BUSI_EXCHANGE,true,false);
    }
    
    //通配符路由模式
    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE,true,false);
    }

    @Bean("addUserQueue")
    public Queue addUserQueue(){
        return new Queue(RabbitMqConstants.QUEUE_ADD_USER,true);
    }

    @Bean("addUserBinding")
    Binding addUserBinding(@Qualifier("addUserQueue") Queue queue,@Qualifier("busiDirectExchange") DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(RabbitMqConstants.ROUTINGKEY_ADD_USER);
    }

    @Bean("updateUserQueue")
    public Queue updateUserQueue(){
        return new Queue(RabbitMqConstants.QUEUE_UPDATE_USER,true);
    }

    @Bean("updateUserBinding")
    Binding updateUserBinding(@Qualifier("updateUserQueue")Queue queue,@Qualifier("busiDirectExchange") DirectExchange directExchange){
    	return BindingBuilder.bind(queue).to(directExchange).with(RabbitMqConstants.ROUTINGKEY_UPDATE_USER);
    }
    
    @Bean("saveLogQueue")
    public Queue queryLogQueue(){
        return new Queue(RabbitMqConstants.QUEUE_SAVE_LOG,true);
    }
    
    @Bean("saveLogBinding")
    Binding saveLogBinding(@Qualifier("saveLogQueue")Queue queue,@Qualifier("topicExchange") TopicExchange topicExchange){
    	return BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstants.ROUTINGKEY_LOG_SEND);
    }
    
    @Bean
	public Jackson2JsonMessageConverter jackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
    
}
