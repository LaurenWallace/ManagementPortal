package com.resort.pojos;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimburseId;
	private int requesterId;
	private int resolverId;
	private int status_Id;
	private int type_Id;
	//When we want to display status type & type name
	private String statName;
	private String typeName;
	
	private String receipt;
	private String reason;
	private String rNotes;
	//When we want to display requester & resolver names
	private String reqName;
	private String resName;
	private double amount;
	private Timestamp sdate;
	private Timestamp rdate;

	public Reimbursement() {};
	
	public Reimbursement(int reimburseId, int requesterId, int status_Id, String reason, String rNotes, 
			double amount, int type_Id) {
		super();
		this.reimburseId = reimburseId;
		this.requesterId = requesterId;
		this.status_Id = status_Id;
		this.reason = reason;
		this.rNotes = rNotes;
		this.amount = amount;
		this.type_Id = type_Id;
	}

	public Reimbursement(int reimburseId, int requesterId, int resolverId, int status_Id, String receipt, String reason,
			String rNotes, double amount, int type_Id) {
		super();
		this.reimburseId = reimburseId;
		this.requesterId = requesterId;
		this.resolverId = resolverId;
		this.status_Id = status_Id;
		this.receipt = receipt;
		this.reason = reason;
		this.rNotes = rNotes;
		this.amount = amount;
		this.type_Id = type_Id;
	}

	public Reimbursement(int reimburseId, String statName, String typeName, String receipt, String reason,
			String rNotes, String reqName, String resName, double amount, Timestamp sdate, Timestamp rdate) {
		super();
		this.reimburseId = reimburseId;
		this.statName = statName;
		this.typeName = typeName;
		this.receipt = receipt;
		this.reason = reason;
		this.rNotes = rNotes;
		this.reqName = reqName;
		this.resName = resName;
		this.amount = amount;
		this.sdate = sdate;
		this.rdate = rdate;
	}

	public Reimbursement(int reimid, String reqfn, String reqln, double amount, String reason, String receipt,
			String statn, String typen, Timestamp start, String resfn, String resln, Timestamp close, String notes) {
		
		this.reimburseId = reimid;
		this.reqName = reqfn + " " + reqln;
		this.amount = amount;
		this.reason = reason;
		this.receipt = receipt;
		this.statName = statn;
		this.typeName = typen;
		this.sdate    = start;
		this.resName  = resfn + " " + resln;
		this.rdate    = close;
		this.rNotes   = notes;
	}

	public int getReimburseId() {
		return reimburseId;
	}

	public void setReimburseId(int reimburseId) {
		this.reimburseId = reimburseId;
	}

	public int getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatus_Id() {
		return status_Id;
	}

	public void setStatus_Id(int status_Id) {
		this.status_Id = status_Id;
	}
	
	public int getType_Id() {
		return type_Id;
	}

	public void setType_Id(int type_Id) {
		this.type_Id = type_Id;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getrNotes() {
		return rNotes;
	}

	public void setrNotes(String rNotes) {
		this.rNotes = rNotes;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Timestamp getSdate() {
		return sdate;
	}

	public void setSdate(Timestamp sdate) {
		this.sdate = sdate;
	}

	public Timestamp getRdate() {
		return rdate;
	}

	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	
	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	@Override
	public String toString() {
		return "Reimbursement Id: " + reimburseId + "\n Requester Id: " + requesterId + "\n Resolver Id: "
				+ resolverId + "\n Status Id: " + status_Id + "\n Reason: " + reason + "\n Resolver Notes: " + rNotes + "\n Amount Requested: "
				+ amount;
	}

}
