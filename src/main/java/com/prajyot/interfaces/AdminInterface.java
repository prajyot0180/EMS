package com.prajyot.interfaces;

public interface AdminInterface {

	public boolean usernameExist(String username);
	public void addAdmin(String name, String gender, String username, String mobilenumber, String password);
	public boolean forgotPassword(String USERNAME, String PASSWORD);
	public boolean login(String USERNAME, String PASSWORD) ;
	
}
