package com.resort.dto;

import java.util.ArrayList;

import com.resort.pojos.Reimbursement;
import com.resort.pojos.User;

public class EmpDTO {

	private User user;
	private ArrayList<Reimbursement> reims;
	
	public EmpDTO(User user, ArrayList<Reimbursement> reims) {
		super();
		this.user = user;
		this.reims = reims;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Reimbursement> getReims() {
		return reims;
	}

	public void setReims(ArrayList<Reimbursement> reims) {
		this.reims = reims;
	}
	
}
