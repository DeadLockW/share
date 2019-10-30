package com.share.impl;

import org.springframework.stereotype.Component;

import com.share.IDemoService;

@Component
public class DemoServiceImpl implements IDemoService {

	@Override
	public String getDate() {
		return "hello SpringCloud";
	}
}
