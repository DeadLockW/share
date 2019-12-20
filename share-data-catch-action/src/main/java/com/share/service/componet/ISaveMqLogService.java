package com.share.service.componet;

import com.share.entity.MsgMqLog;

public interface ISaveMqLogService {
	
	/**
	 * 保存mq消息到数据
	 * @param dto
	 */
	public MsgMqLog saveMqLog(String busiType, String actionType, String msgId, String mgs);

	/**
	 * 更新mq状态
	 * @param dto
	 */
	public MsgMqLog updateMqLog(String mqStatus ,String msgId);	
}
