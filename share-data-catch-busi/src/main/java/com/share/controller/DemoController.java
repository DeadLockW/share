package com.share.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.share.IDemoService;

@RestController
@RequestMapping("test")
public class DemoController {
	
	@Resource
	private IDemoService iDemoService;

	@RequestMapping("/testMethed")
	@ResponseBody
	public String test() {
		return iDemoService.getDate();
	}
}
