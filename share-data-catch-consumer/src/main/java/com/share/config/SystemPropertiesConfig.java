package com.share.config;

import java.util.Properties;
import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;

@Component
public class SystemPropertiesConfig {

	private static Properties properties = new Properties();
	
	@NacosInjected
	private NacosConfigService configService;
	
	private final String DATA_ID = "share-data-catch-consumer.properties";
	
	private final String GROUP_NAME = "DEFAULT_GROUP";
	
	public static String getVaule(String key) {
		return properties.getProperty(key);
	}

//	@PostConstruct
//	public void initData() throws NacosException{
//		configService.addListener(DATA_ID, GROUP_NAME, new Listener() {
//			
//			@Override
//			public void receiveConfigInfo(String configInfo) {
//				System.out.println("................."+configInfo);
//			}
//			
//			@Override
//			public Executor getExecutor() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
//	}
	
	@NacosConfigListener(dataId = DATA_ID)
	public void onMessage(String config) {
		String dataString = config;
	    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dataString);
	}
	
}
