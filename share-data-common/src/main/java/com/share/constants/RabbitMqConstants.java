package com.share.constants;

import sun.misc.JavaAWTAccess;

public class RabbitMqConstants {

    /**
     * 业务处理交换器
     */
    public static final String QUERY_EXCHANGE = "query.exchange";
    /**
    * 业务查询交换器
    */
    public static final String BUSI_EXCHANGE = "busi.exchange";

    /**
    * 获取用户信息queue
    */
    public static final String QUEUE_QUERY_USER = "query.user";

    /**
    * 添加用户queue
    */
    public static final String QUEUE_ADD_USER = "add.user";

    /**
    *获取用户信息routKey
    */
    public static final String ROUTINGKEY_QUERY_USER = "query.user";

    /**
    * 添加用户routKey
    */
    public static final String ROUTINGKEY_ADD_USER = "add.user";
}
