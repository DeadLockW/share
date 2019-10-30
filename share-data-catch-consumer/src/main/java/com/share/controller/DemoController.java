package com.share.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.share.service.impl.rpc.IRpcDemoService;

@RestController
@RequestMapping("demo/api")
@RefreshScope
public class DemoController {

	@Resource
	private IRpcDemoService demoService;
	
	@Value("${test.name:}")//读取nacos配置中心配置的value
	private String name;
	
	 @Value("${ahfl.title:}")
     private String title;

	@RequestMapping(method = RequestMethod.GET, path = "/demo")
	public String getData() {
		System.out.println("The title is ========= >> : " + name);
		return demoService.test();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/testNacosConfig")
	public String testNacosConfig() {
		System.out.println("The name is ========= >> : " + name);
		return name;
	}

}
