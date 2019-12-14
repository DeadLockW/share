package com.share.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
@EnableScheduling
@Slf4j
public class TimeTask {

    @Resource
    private RedisTemplate redisTemplate;

    @Scheduled(fixedRate=5000)
    public void getValue() {
        redisTemplate.opsForValue().set("age",20);
        Object age  = redisTemplate.opsForValue().get("age");
        log.info("===============从redis获取到姓名："+age.toString());
    }
}
