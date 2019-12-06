package com.share.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseReqDto<T> implements Serializable{
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
    private final ObjectMapper mapper = new ObjectMapper();

	private BaseHeader header;
	
	private T body;
	
	@JsonIgnore
    public <T> T getBodyObject(Class<T> clazz) {
        if(body != null) {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.convertValue(body, clazz);
        }
        return null;
    }

}
