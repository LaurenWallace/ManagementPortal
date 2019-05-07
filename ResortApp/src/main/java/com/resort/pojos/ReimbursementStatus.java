package com.resort.pojos;

public class ReimbursementStatus {
	
	private int statId;
	private String name;
	
	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(int statId, String name) {
		super();
		this.statId = statId;
		this.name = name;
	}

	public int getStatId() {
		return statId;
	}

	public void setStatId(int statId) {
		this.statId = statId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
