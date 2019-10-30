package com.share.proxy;

public class UserManagerImpl implements IUserManager{

	@Override
	public void addUser(String name) {
		System.out.println("添加用户成功："+name);
	}

	@Override
	public void delUser(String userId) {
		System.out.println("删除用户成功："+userId);
	}
}
