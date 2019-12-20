package com.share.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.constants.Contansts;
import com.share.constants.MqStatusContants;
import com.share.constants.RabbitMqConstants;
import com.share.entity.MsgMqLog;
import com.share.mapper.MsgMqLogMapper;
import com.share.mq.RabbitSender;
import com.share.service.componet.ISaveMqLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时检查消息表中没有确认得消息或者发送失败得小，并重新发送，失败三次不在发送
 * @author wk
 *
 */
@Configuration
@EnableScheduling
@Slf4j
public class Schedule {
	
	@Resource
	private ISaveMqLogService iSaveMqLogService;
	
	@Resource
	private MsgMqLogMapper msgMqLogMapper;
	
	@Resource
	private RabbitSender rabbitSender;
	
	@Scheduled(cron = "0 0/30 * * * ?")
	public void excute() {
		
		log.info("定时任务开始执行");
		QueryWrapper<MsgMqLog> queryWrapper = new QueryWrapper<MsgMqLog>();
		queryWrapper.eq("is_delete", Contansts.ZERO_0);
		queryWrapper.le("send_count", Contansts.THREE_3);
		queryWrapper.in("mq_status", MqStatusContants.MQ_STATUS_0,MqStatusContants.MQ_STATUS_1,MqStatusContants.MQ_STATUS_2,MqStatusContants.MQ_STATUS_4);
		List<MsgMqLog> list = msgMqLogMapper.selectList(queryWrapper);
		for (MsgMqLog msgMqLog : list) {
			String msgBody = msgMqLog.getMsg();
			rabbitSender.send(RabbitMqConstants.BUSI_EXCHANGE,RabbitMqConstants.ROUTINGKEY_ADD_USER,msgBody, msgMqLog.getMsgId());
			iSaveMqLogService.updateMqLog(MqStatusContants.MQ_STATUS_2, msgMqLog.getMsgId());
		}
	}

}
