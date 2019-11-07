package com.share.enums;

public enum ActionTypeEnum {

	GET_USER_INFO_LIST(1, "getUserInfoList", "查询用户列表"),
	
	ADD_USER_INFO(2, "addBaseUser", "新增用户"),

	QUERY_USER_INFO_BY_ID(3,"getUserInfoById","根据id获取用户"),

	UPDATE_USER_INFO(4,"updateBaseUser","编辑用户");

	private int id;
	private String actionName;
	private String describe;

	private ActionTypeEnum(int id, String actionName, String describe) {
		this.id = id;
		this.actionName = actionName;
		this.describe = describe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public static ActionTypeEnum fromCode(String actionName) {
		if (actionName == null || actionName.isEmpty()) {
			return null;
		}
		for (ActionTypeEnum actionTypeEnum : ActionTypeEnum.values()) {
			if (actionName.equals(actionTypeEnum.getActionName())) {
				return actionTypeEnum;
			}
		}
		return null;
	}

}
