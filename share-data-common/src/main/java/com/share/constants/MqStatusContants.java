package com.share.constants;

/**
 * mq状态常量
 * @author wk
 *
 */
public interface MqStatusContants {
	
	final static String MQ_STATUS_0 = "0";//发送成功
	final static String MQ_STATUS_1 = "1";//未发送
	final static String MQ_STATUS_2 = "2";//已发送
	final static String MQ_STATUS_3 = "3";//发送失败
	final static String MQ_STATUS_4 = "4";//确认消费
	final static String MQ_STATUS_5 = "5";//决绝消费
	final static String MQ_STATUS_6 = "6";//消息过期

}
