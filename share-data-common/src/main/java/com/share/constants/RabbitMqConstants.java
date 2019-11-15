package com.share.constants;

import sun.misc.JavaAWTAccess;

public class RabbitMqConstants {

    /**
    * 业务查询交换器
    */
    public static final String BUSI_EXCHANGE = "busi.exchange";

    /**
    * 编辑户信息queue
    */
    public static final String QUEUE_UPDATE_USER = "update.user";

    /**
    * 添加用户queue
    */
    public static final String QUEUE_ADD_USER = "add.user";

    /**
    *编辑户信息routKey
    */
    public static final String ROUTINGKEY_UPDATE_USER = "update.user";

    /**
    * 添加用户routKey
    */
    public static final String ROUTINGKEY_ADD_USER = "add.user";
}
