package com.share.service.impl.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "share-data-catch-busi")
public interface IRpcDemoService {

	@GetMapping(value="test/testMethed")
	public String test();
	
}
