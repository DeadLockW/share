package com.share.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseEntity {
	
    /**
     * 创建时间
     */
	@ApiModelProperty(hidden = true)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
	@ApiModelProperty(hidden = true)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 逻辑删除标记(0-未删除 1-已删除)
     */
	@ApiModelProperty(hidden = true)
    private String isDelete;

}
