package com.share.proxy;

public class UserManagerProxy implements IUserManager {
	
	private IUserManager iUserManager;

	public UserManagerProxy(IUserManager iUserManager) {
		this.iUserManager = iUserManager;
	}

	@Override
	public void addUser(String name) {
		iUserManager.addUser(name);
		System.out.println("代理添加用户成功");
	}

	@Override
	public void delUser(String userId) {
		iUserManager.delUser(userId);
		System.out.println("代理删除用户成功");
	}

}
