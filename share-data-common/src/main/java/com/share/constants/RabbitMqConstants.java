package com.share.constants;

public class RabbitMqConstants {

    /**
    * 业务查询交换器
    */
    public static final String BUSI_EXCHANGE = "busi.exchange";
    
    /**
     * 业务查询交换器
     */
     public static final String TOPIC_EXCHANGE = "topic.exchange";

    /**
    * 编辑户信息queue
    */
    public static final String QUEUE_UPDATE_USER = "update.user";

    /**
    * 添加用户queue
    */
    public static final String QUEUE_ADD_USER = "add.user";
    
    /**
     * 添加logqueue
     */
     public static final String QUEUE_ADD_LOG = "add.log";
     
     /**
      * 添加logqueue
      */
      public static final String QUEUE_QUERY_LOG = "query.log";

    /**
    *编辑户信息routKey
    */
    public static final String ROUTINGKEY_UPDATE_USER = "update.user";

    /**
    * 添加用户routKey
    */
    public static final String ROUTINGKEY_ADD_USER = "add.user";
    
    /**
     * 日志传输路由ley
     */
     public static final String ROUTINGKEY_LOG_SEND = "*.log";
}
