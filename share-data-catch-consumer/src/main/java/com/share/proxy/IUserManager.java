package com.share.proxy;

public interface IUserManager {

	public void addUser(String name);
	
	public void delUser(String userId);
	
	default String a(String b) {
		return "1";
	};
}
