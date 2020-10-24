package com.share;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import com.share.constants.ProfileConstants;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients
@EnableRabbit
@MapperScan(ActionServiceApplication.COM_SHARE_MAPPER)
@EnableAspectJAutoProxy
@RefreshScope
public class ActionServiceApplication {
	
	static final String COM_SHARE_MAPPER = "com.share.mapper";

	private static final Logger log = LoggerFactory.getLogger(ActionServiceApplication.class);
	 
    public static void main(String[] args) throws UnknownHostException{
        SpringApplication application = new SpringApplication(ActionServiceApplication.class);
		addDefaultProfile(application);
		
		Environment environment = application.run(args).getEnvironment();
		String protocol = "http";
		if (environment.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}

		String configUrl = environment.getProperty("spring.cloud.nacos.config.server-addr");

		String port = environment.getProperty("server.port");
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
						+ "External: \t{}://{}:{}\n\t" + "Config Server: \t{}\n\t"
						+ "Profile(s): \t{}\n----------------------------------------------------------",
				environment.getProperty("spring.application.name"), protocol, port,
				protocol, InetAddress.getLocalHost().getHostAddress(), port,
				(configUrl == null ? "" : configUrl), environment.getActiveProfiles());
    }
    
    private static void addDefaultProfile(SpringApplication app) {
		Map<String, Object> defProperties = new HashMap<>();
		/*
		 * 当不存在其它的profile文件时，使用application.yml,
		 * 存在其它profile时，则加载application.yml和相应的profile的配置文件
		 */
		defProperties.put(ProfileConstants.SPRING_PROFILE_DEFAULT, ProfileConstants.SPRING_PROFILE_DEFAULT_VAL);
		app.setDefaultProperties(defProperties);
	}
}