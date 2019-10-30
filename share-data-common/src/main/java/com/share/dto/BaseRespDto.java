package com.share.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class BaseRespDto<T> implements Serializable{

	private String code;
	private String msg;
	private T date;
	
	public static BaseRespDto build(String code, String msg, Object obj) {
		return new BaseRespDto(code, msg, obj);
	}
}
