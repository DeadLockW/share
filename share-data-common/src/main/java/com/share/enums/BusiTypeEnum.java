package com.share.enums;

public enum BusiTypeEnum {

	BUSI_SERVICE_1001(1,"BS1001","业务处理"),
	
	QUERY_SERVICE_1002(2,"BS1002","业务查询");
	
	private int id;
	private String busiType;
	private String busiName;
	
	private BusiTypeEnum(int id, String busiType, String busiName) {
		this.id = id;
		this.busiType = busiType;
		this.busiName = busiName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}
	
	public static BusiTypeEnum fromCode(String busiType) {
        if(busiType == null || busiType.isEmpty()) {
            return null;
        }
        for (BusiTypeEnum busiTypeEnum: BusiTypeEnum.values()) {
            if(busiType.equals(busiTypeEnum.getBusiType())) {
                return busiTypeEnum;
            }
        }
        return null;
    }
	
}
