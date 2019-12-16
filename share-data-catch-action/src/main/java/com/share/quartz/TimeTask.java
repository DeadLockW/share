package com.share.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
@EnableScheduling
@Slf4j
public class TimeTask {

    /**
     *
     * 这里用简单的String数据结构，使用StringRedisTemplate，因为针对不同的数据结构做了不同的序列化方式（如果手动设置序列化那么会以二进制的方式存储）
     *
     * */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedRate=5000)
    public void getValue() {
        stringRedisTemplate.opsForValue().set("name","wk");
        stringRedisTemplate.opsForValue().set("age","11");
        String age  = (String) stringRedisTemplate.opsForValue().get("age");
        String name  = (String) stringRedisTemplate.opsForValue().get("name");
        log.info("===============从redis获取到年龄："+age);
        log.info("===============从redis获取到姓名："+name);
    }
}