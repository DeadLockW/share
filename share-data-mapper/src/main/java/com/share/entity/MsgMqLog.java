package com.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.share.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wk
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MsgMqLog对象", description = "")
public class MsgMqLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键id")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "业务类型")
	private String busiType;

	@ApiModelProperty(value = "操作函数名")
	private String actionType;

	@ApiModelProperty(value = "消息id")
	private String msgId;

	@ApiModelProperty(value = "消息体")
	private String msg;

	@ApiModelProperty(value = "发送次数")
	private String sendCount;

	@ApiModelProperty(value = "发送状态  0：未发送  1：已发送  2：发送失败")
	private String sendStatus;

	@ApiModelProperty(value = "消息状态 0：发送成功  1：未发送  2：发送失败  3：已确认消息  4：已拒接消息 5：消息已过期")
	private String mqStatus;

}
