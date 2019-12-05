package com.share.constants;

public class RabbitMqConstants {

	
/*****************************交换机********************************/
    /**
    * 业务查询交换器
    */
    public static final String BUSI_EXCHANGE = "busi.exchange";
    
    /**
     * 业务查询交换器
     */
     public static final String TOPIC_EXCHANGE = "topic.exchange";

     
/*****************************队列********************************/
    /**
    * 编辑户信息queue
    */
    public static final String QUEUE_UPDATE_USER = "update.user";

    /**
    * 添加用户queue
    */
    public static final String QUEUE_ADD_USER = "add.user";
    
  /**
   * 添加log queue 日志记录队列
	*/
    public static final String QUEUE_SAVE_LOG = "save.log";
      
      
 /*****************************路由key********************************/
    /**
    *编辑户信息routKey
    */
    public static final String ROUTINGKEY_UPDATE_USER = "update.user";

    /**
    * 添加用户routKey
    */
    public static final String ROUTINGKEY_ADD_USER = "add.user";
    
    /**
     * 查询用户routKey（用于记录日志）
     */
     public static final String ROUTINGKEY_QUERY_USER = "query.user";
    
    /**
     * 日志传输路由Key（通配符路由模式A，所有匹配到的路由B，只要消息发送到了与这些路由B绑定的交换机上，与A绑定的队列就可以获取到这些消息）
     */
     public static final String ROUTINGKEY_LOG_SEND = "*.user";
}
