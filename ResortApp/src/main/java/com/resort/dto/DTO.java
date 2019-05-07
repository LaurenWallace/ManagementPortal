package com.resort.dto;

import com.resort.pojos.User;

public class DTO {
	
	private User user;

	public DTO(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
