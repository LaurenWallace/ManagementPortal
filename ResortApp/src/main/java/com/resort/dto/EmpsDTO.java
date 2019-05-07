package com.resort.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.resort.pojos.Reimbursement;
import com.resort.pojos.User;

public class EmpsDTO {
	
	private User user;
	private ArrayList<User> users;
	
	public EmpsDTO(User user, ArrayList<User> users) {
		super();
		this.user = user;
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
}
