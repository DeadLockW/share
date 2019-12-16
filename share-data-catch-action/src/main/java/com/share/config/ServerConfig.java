package com.share.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * description: 通过代码配置项目端口（暂不使用，使用配置文件配置端口） <br>
 * date: 2019/11/18 14:54 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
@Configuration
@Slf4j
public class ServerConfig {

    @Value("${spring.redis.host}")
    private String host;

    public static Properties properties = new Properties();

    @PostConstruct
    public void init(){
        properties.setProperty("host",host);
        log.info("==============初始化数据成功："+properties.getProperty("host"));
    }
}
