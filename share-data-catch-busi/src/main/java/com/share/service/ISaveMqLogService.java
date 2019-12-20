package com.share.service;

public interface ISaveMqLogService {
	
	/**
	 * 保存mq消息到数据
	 * @param dto
	 */
	public void saveMqLog(String busiType, String actionType, String msgId, String mgs);

	/**
	 * 更新mq状态
	 * @param dto
	 */
	public void updateMqLog(String mqStatus ,String msgId);	
}
