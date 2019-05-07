package com.resort.dao;

import java.util.ArrayList;

import com.resort.pojos.Reimbursement;

/*
 * This DAO handles interactions with the resort_users table
 * The following methods should be implemented by the class implementing this:
 * - get()
 * - Reimbursement getReimbursementById(int rid) rid-> reimbursementid
 * - int addReimbursement(int requesterid, double amount, int reimbursement type)
 * - int addDetailReimnursement(int requester, String reason, double amount, int reimbursement type)
 * -
 */

public interface ReimburseDAO {

	//this returns a reimbursement object when a reimbursement id is passed in
	public Reimbursement getReimById(int rid);
	
	//this returns a reimbursement arraylist when a user object is passed in
	//the arraylist has the reimbursements associated with that user
	public ArrayList<Reimbursement> getUserReimbursements(int uid);
	
	//this returns a reimbursement arraylist holding reimbursements
	public ArrayList<Reimbursement> getReimbursements();
	
	//this adds a reimbursement row to the table and returns the reimbursement id 
	public int addReimbursement(int requester, double amount, int rtype);
	
	//this adds a reimbursement row to the table with a reason and returns the reimbursement id
	public int addDetailReimbursement(int requester, String reason, double amount, int rtype);
	
	//this adds a reimbursement row to the table with a reason & receipt then returns the reimbursement id
	public int addFullReimbursement(int requester, String reason, double amount, String receipt, int rtype);
	
	//this updates the reimbursement status when a manager changes the reimbursement status to approved or denied
	public void updateReimbursementStatus(int rid, int nstat, int resid);
	
	//this updates the reimbursement notes when a manager adds notes
	//this must be a callable statment to have the resolve date posted
	public void updateReimbursementNotes(int rid, String notes);
	
}
