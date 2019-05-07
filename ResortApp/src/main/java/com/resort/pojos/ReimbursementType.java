package com.resort.pojos;

public class ReimbursementType {
	
	private int typeId;
	private String name;
	
	public ReimbursementType() {
		super();
	}

	public ReimbursementType(int typeId, String name) {
		super();
		this.typeId = typeId;
		this.name = name;
	}

	public int gettypeId() {
		return typeId;
	}

	public void settypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Reimbursement Type: " + name;
	}

}
