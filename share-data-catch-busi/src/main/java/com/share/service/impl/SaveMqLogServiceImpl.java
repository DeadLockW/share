package com.share.service.impl;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.MsgMqLog;
import com.share.mapper.MsgMqLogMapper;
import com.share.service.ISaveMqLogService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SaveMqLogServiceImpl implements ISaveMqLogService {
	
	@Resource
	private MsgMqLogMapper msgMqLogMapper;
	
	@Resource
	private ExecutorService newFixedThreadPool;

	@Override
	public void saveMqLog(String busiType, String actionType, String msgId, String mgs) {
		
		try {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					MsgMqLog msgMqLog = new MsgMqLog();
					msgMqLog.setBusiType(busiType);
					msgMqLog.setActionType(actionType);
					msgMqLog.setMsgId(msgId);
					msgMqLog.setCreateTime(new Date());
					msgMqLog.setUpdateTime(new Date());
					msgMqLogMapper.insert(msgMqLog);
					log.info("==========消息记录成功");
				}
			});
		} catch (Exception e) {
			log.error("==========消息记录失败：" + e);
		}
	}
	
	@Override
	public void updateMqLog(String mqStatus ,String msgId) {
		
		try {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
				QueryWrapper<MsgMqLog> queryWrapper = new QueryWrapper<MsgMqLog>();
				queryWrapper.eq("msg_id", msgId);
				MsgMqLog msgMqLog = msgMqLogMapper.selectOne(queryWrapper);
				msgMqLog.setMqStatus(mqStatus);
				msgMqLog.setUpdateTime(new Date());
				msgMqLogMapper.updateById(msgMqLog);
				log.info("==========消息状态更新成功");
			}
		});
		} catch (Exception e) {
			log.error("==========消息状态更新失败：" + e);
		}
	}

}
