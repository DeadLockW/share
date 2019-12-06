package com.share.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseHeader implements Serializable{

	private String transType;
	
	private String transName;
	
	private String transId;
	
	private String channleCode;
	
	private String timestamp;
}
