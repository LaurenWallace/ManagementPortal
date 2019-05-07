package com.resort.dao;

import java.util.HashMap;

import com.resort.pojos.User;

/*
 * This DAO handles interactions with the resort_users table
 * The following methods should be implemented by the class implementing this:
 * - getEmails()
 * - getUserById(int id)
 * - addUser(String fn, String ln, String email, String pass)
 * - 
 */

public interface UserDAO {
	
	//Returns a HashMap of a resort user's user id & email
	//This will be used by Service to check if a person has a valid email
	public HashMap<Integer, String> getEmails();
	
	//Gets a user by their user id
	public User getUserById(int id);
	
	//Returns a user's id, adds the user to the database
	public void addUser(String fn, String ln, String email, String pass);
	
	//Updates the user's selected information: firstname, lastname, email, password
	public void updateUserFN(String fn, int uid);
	public void updateUserLN(String ln, int uid);
	public void updateUserEM(String em, int uid);
	public void updateUserPW(String pw, int uid);

}
