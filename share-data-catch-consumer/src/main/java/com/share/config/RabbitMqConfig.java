//package com.share.config;
//
//import com.share.constants.RabbitMqConstants;
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * description: mq配置 direct模式
// * date: 2019/11/7 15:29 <br>
// * author: wk <br>
// * version: 1.0 <br>
// */
//@Configuration
//public class RabbitMqConfig {
//
//
//    /************************绑定业务处理队列和路由*******************************/
//
//    @Bean("busiDirectExchange")
//    public DirectExchange busiDirectExchange(){
//        return new DirectExchange(RabbitMqConstants.BUSI_EXCHANGE);
//    }
//
//    @Bean("addUserQueue")
//    public Queue addUserQueue(){
//        return new Queue(RabbitMqConstants.QUEUE_ADD_USER);
//    }
//
//    @Bean("addUserBinding")
//    Binding addUserBinding(@Qualifier("addUserQueue") Queue queue,@Qualifier("busiDirectExchange") DirectExchange directExchange){
//        return BindingBuilder.bind(queue).to(directExchange).with(RabbitMqConstants.ROUTINGKEY_ADD_USER);
//    }
//
//    @Bean("updateUserQueue")
//    public Queue updateUserQueue(){
//        return new Queue(RabbitMqConstants.QUEUE_UPDATE_USER);
//    }
//
//    @Bean("updateUserBinding")
//    Binding updateUserBinding(@Qualifier("updateUserQueue")Queue queue,@Qualifier("busiDirectExchange") DirectExchange directExchange){
//        return BindingBuilder.bind(queue).to(directExchange).with(RabbitMqConstants.ROUTINGKEY_UPDATE_USER);
//    }
//}
