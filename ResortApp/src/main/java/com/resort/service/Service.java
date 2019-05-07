package com.resort.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.resort.dao.RSDAOImpl;
import com.resort.dao.RTDAOImpl;
import com.resort.dao.ReimburseDAOImpl;
import com.resort.dao.UserDAOImpl;
import com.resort.pojos.Reimbursement;
import com.resort.pojos.User;

public class Service {
	
	private UserDAOImpl udao;
	private ReimburseDAOImpl rdao; 
	//private static RSDAOImpl rsdao;
	//private static RTDAOImpl rtdao;
	
	//this is known as dependency injection in a constructor
	public Service(UserDAOImpl udao, ReimburseDAOImpl rdao) {
		this.udao = udao;
		this.rdao = rdao; 
	}
	
	/*
	 * Returns -1 if user is not found
	 * Otherwise returns the user id
	 */
	public int validateUser(String email){
		
		int id = -1;
		HashMap<Integer, String> users = udao.getEmails();

		for(Integer n:users.keySet()){
			if(users.get(n).equalsIgnoreCase(email)){
				id = n;
			}
		}

		return id;
	}
	
	/*
	 * 
	 */
	public User login(int id, String pass){
		User u = udao.getUserById(id);
		System.out.println(pass);
		System.out.println(u.getPassword());
		
		if(u.getPassword().equalsIgnoreCase(pass)){
			return u;
		}
		else {return null;}
	}
	
	public void addUser(User u){
		System.out.println(u.toString());
		udao.addUser(u.getFirstname(), u.getLastname(), u.getEmail(), u.getPassword());
	}
	
	public void addReimbursement(User u, double amount, int rtype){
		rdao.addReimbursement(u.getUserid(), amount, rtype);
	}
	
	public void addReasonReimbursement(User u, String reason, double amount, int rtype){
		rdao.addDetailReimbursement(u.getUserid(), reason, amount, rtype);
	}
	
	public void addFullReimbursement(User u, String reason, double amount, String receipt, int rtype) {
		rdao.addFullReimbursement(u.getUserid(), reason, amount, receipt, rtype);
	}
	
	public void updateReimbursement(int rid, int nstat, User u) {
		rdao.updateReimbursementStatus(rid, nstat, u.getUserid());
	}
	
	public void updateReimbursementNote(int rid, String notes) {
		rdao.updateReimbursementNotes(rid, notes);
	}
	
	public void updateUserFN(String change, int uid) {
		udao.updateUserFN(change, uid);
	}
	
	public void updateUserLN(String change, int uid) {
		udao.updateUserLN(change, uid);
	}
	
	public void updateUserEM(String change, int uid) {
		udao.updateUserEM(change, uid);
	}
	
	public void updateUserPW(String change, int uid) {
		udao.updateUserPW(change, uid);
	}
	
	public User getUser(int id) {
		return udao.getUserById(id);
	}
	
	public ArrayList<User> getUsers() {
		return udao.getUsers();
	}

	public ArrayList<Reimbursement> getUserReimbursements(User u, int uid) {
		//if (u.getIsManager() == 0) {
				return rdao.getUserReimbursements(u.getUserid());
		//}
			//return rdao.getUserReimbursements(uid);
	}
	
	public ArrayList<Reimbursement> getReimbursementsByStat(int stat) {
		return rdao.getReimbursementsByStatus(stat);
	}
	
	public ArrayList<Reimbursement> getAllReimbursements() {
		return rdao.getReimbursements();
	}
	
	/*public HashMap<Integer, String> getRTypes() {
		return rtdao.getRT();
	}
	
	public String getTypeName(int tid) {
		HashMap<Integer, String> rtmap = rtdao.getRT();
		return rtmap.get(tid);
	}
	
	public HashMap<Integer, String> getRStats() {
		return rsdao.getRS();
	}
	
	public String getStatName(int sid) {
		HashMap<Integer, String> rsmap = rsdao.getRS();
		return rsmap.get(sid);
	}*/
	
}
