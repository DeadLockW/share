package com.share.proxy;

public class Client {

	public static void main(String[] args) {
//		IUserManager iUserManager = new UserManagerProxy(new UserManagerImpl());
//		iUserManager.addUser("wk");
		aa(new Demo());
	}
	
	public static void aa(UserManagerImpl l) {
		System.out.println("aaa");
	}
}
