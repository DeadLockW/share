package com.share.quartz;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void excute() {
		log.info("==========定时任务开始执行");
	}

    
}
