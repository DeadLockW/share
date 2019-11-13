package com.share.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户实基本信息体类
 * </p>
 * 
 * @author wk
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(description = "用户实体类")
public class BaseUserInfo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
	private Long id;

	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String userRealName;

	/**
	 * 身份证号
	 */
	@ApiModelProperty("身份证号")
	private String userId;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String phoneNum;

	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
	private String address;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	private String nickName;

	/**
	 * 年龄
	 */
	@ApiModelProperty("年龄")
	private Integer userAge;

	/**
	 * 性别
	 */
	@ApiModelProperty("性别")
	private String userSex;

	/**
	 * 登录密码
	 */
	@ApiModelProperty("登录密码")
	private String loginPassword;

	/**
	 * 职业类型
	 */
	@ApiModelProperty("职业类型")
	private String professional;

	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String emailAdd;

	/**
	 * 学校
	 */
	@ApiModelProperty("学校")
	private String school;

	/**
	 * 专业
	 */
	@ApiModelProperty("专业")
	private String major;

}
