package com.share.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: 通过代码配置项目端口（暂不使用，使用配置文件配置端口） <br>
 * date: 2019/11/18 14:54 <br>
 * author: wk <br>
 * version: 1.0 <br>
 */
//@Configuration
//public class ServerConfig {
//
//    static final String port = "8888";
//
//    @Bean
//    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
//        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableServletWebServerFactory factory) {
//                factory.setPort(Integer.parseInt(port));
//            }
//        };
//    }
//}
