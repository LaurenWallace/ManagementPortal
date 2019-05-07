
package com.resort.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.resort.pojos.Reimbursement;
import com.resort.util.ConnectionFactory;

public class ReimburseDAOImpl implements ReimburseDAO {

	@Override
	public Reimbursement getReimById(int rid) {
		Reimbursement reim = new Reimbursement();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String sql = "select * from reimbursement where reimburse_id =  ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rid);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				reim.setReimburseId(info.getInt(1));
				reim.setType_Id(info.getInt(2));
				reim.setAmount(info.getDouble(3));
				reim.setRequesterId(info.getInt(4));
				reim.setResolverId(info.getInt(5));
				reim.setStatus_Id(info.getInt(6));
				reim.setReason(info.getString(7));
				reim.setReceipt(info.getString(8));
				reim.setrNotes(info.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reim;
	}
	
	public ArrayList<Reimbursement> getUserReimbursements(int uid) {
		ArrayList<Reimbursement> currentList = new ArrayList<Reimbursement>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql      = "select r.reimburse_id, su.first_name, su.last_name, r.amount, \r\n" + 
							   "r.reason, r.receipt, rs.status_name, rt.type_name,\r\n" + 
							   "r.start_date, ru.first_name, ru.last_name, r.resolve_date, r.resolver_notes\r\n" + 
							   "from reimbursement r\r\n" + 
							   "left join resort_users su\r\n" + 
							   "on su.user_id = r.requester_id\r\n" + 
							   "left join resort_users ru\r\n" + 
							   "on ru.user_id = r.resolver_id\r\n" + 
							   "left join reimbursement_status rs\r\n" + 
							   "on rs.status_id = r.status_id\r\n" + 
							   "left join reimbursement_type rt\r\n" + 
							   "on rt.type_id = r.type_id\r\n" + 
							   "where su.user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet info = ps.executeQuery();
			
			while(info.next()) {
				int reimid      = info.getInt(1);
				String reqfn    = info.getString(2);
				String reqln    = info.getString(3);
				double amount   = info.getDouble(4);
				String reason   = info.getString(5);
				String receipt  = info.getString(6);
				String statn    = info.getString(7);
				String typen    = info.getString(8);
				Timestamp start = info.getTimestamp(9);
				String resfn    = info.getString(10);
				String resln    = info.getString(11);
				Timestamp close = info.getTimestamp(12);
				String notes    = info.getString(13);
				
				Reimbursement temp = new Reimbursement(reimid, reqfn, reqln, amount, reason, receipt, statn, typen, start, resfn, resln, close, notes);
				currentList.add(temp);
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println(currentList.toString());
	return currentList;
	}
	
	public ArrayList<Reimbursement> getReimbursementsByStatus(int status) {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String procedure = "select r.reimburse_id, su.first_name, su.last_name, r.amount, \r\n" + 
							   "r.reason, r.receipt, rs.status_name, rt.type_name,\r\n" + 
							   "r.start_date, ru.first_name, ru.last_name, r.resolve_date, r.resolver_notes\r\n" + 
							   "from reimbursement r\r\n" + 
							   "left join resort_users su\r\n" + 
							   "on su.user_id = r.requester_id\r\n" + 
							   "left join resort_users ru\r\n" + 
							   "on ru.user_id = r.resolver_id\r\n" + 
							   "left join reimbursement_status rs\r\n" + 
							   "on rs.status_id = r.status_id\r\n" + 
							   "left join reimbursement_type rt\r\n" + 
							   "on rt.type_id = r.type_id\r\n" + 
							   "where rs.status_id = status";
			
			Statement statement = conn.prepareCall(procedure);
			
			ResultSet rs = statement.executeQuery(procedure);
			
			while(rs.next()) {
				int reimid      = rs.getInt(1);
				String reqfn    = rs.getString(2);
				String reqln    = rs.getString(3);
				double amount   = rs.getDouble(4);
				String reason   = rs.getString(5);
				String receipt  = rs.getString(6);
				String statn    = rs.getString(7);
				String typen    = rs.getString(8);
				Timestamp start = rs.getTimestamp(9);
				String resfn    = rs.getString(10);
				String resln    = rs.getString(11);
				Timestamp close = rs.getTimestamp(12);
				String notes    = rs.getString(13);
				
				Reimbursement temp = new Reimbursement(reimid, reqfn, reqln, amount, reason, receipt, statn, typen, start, resfn, resln, close, notes);
				list.add(temp);
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return list;
	}
	
	public ArrayList<Reimbursement> getReimbursements() {
		
		ArrayList<Reimbursement> currentList = new ArrayList<Reimbursement>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			Statement statement = conn.createStatement();
			String procedure = "select r.reimburse_id, su.first_name, su.last_name, r.amount, \r\n" + 
							   "r.reason, r.receipt, rs.status_name, rt.type_name,\r\n"
							   + "r.start_date, ru.first_name, ru.last_name, r.resolve_date, r.resolver_notes\r\n"
							   + "from reimbursement r\r\n" + "left join resort_users su\r\n"
							   + "on su.user_id = r.requester_id\r\n" + "left join resort_users ru\r\n"
							   + "on ru.user_id = r.resolver_id\r\n" + "left join reimbursement_status rs\r\n"
							   + "on rs.status_id = r.status_id\r\n" + "left join reimbursement_type rt\r\n"
							   + "on rt.type_id = r.type_id";
			
			ResultSet rs = statement.executeQuery(procedure);
			
			while(rs.next()) {
				int reimid      = rs.getInt(1);
				String reqfn    = rs.getString(2);
				String reqln    = rs.getString(3);
				double amount   = rs.getDouble(4);
				String reason   = rs.getString(5);
				String receipt  = rs.getString(6);
				String statn    = rs.getString(7);
				String typen    = rs.getString(8);
				Timestamp start = rs.getTimestamp(9);
				String resfn    = rs.getString(10);
				String resln    = rs.getString(11);
				Timestamp close = rs.getTimestamp(12);
				String notes    = rs.getString(13);
				
				Reimbursement temp = new Reimbursement(reimid, reqfn, reqln, amount, reason, receipt, statn, typen, start, resfn, resln, close, notes);
				
				currentList.add(temp);
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return currentList;
	}

	@Override
	public int addReimbursement(int requester, double amount, int rtype) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into reimbursement (requester_id, amount, type_id) values (?,?,?)";
			String[] key = new String[1];
			key[0] = "reimburse_id";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setInt(1, requester);  //it won't matter what is contained within the String
			ps.setDouble(2, amount);
			ps.setInt(3, rtype);
			
			ps.executeUpdate();
			
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()) {
				id = pk.getInt(1);
			}
			conn.commit();
			return id;
		
		//catch has been tested & works
		} catch (SQLIntegrityConstraintViolationException e) {
    		System.out.println("A reimbursement with that information already exists.");
    		System.out.println("Please try again.");
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int addDetailReimbursement(int requester, String reason, double amount, int rtype) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			Reimbursement r = new Reimbursement();
			
			String sql = "insert into reimbursement (requester_id, reason, amount, type_id) values (?,?,?,?)";
			String[] key = new String[1];
			key[0] = "reimburse_id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, requester);  //it won't matter what is contained within the String
			ps.setString(2, reason);
			ps.setDouble(3, amount);
			ps.setInt(4, rtype);
			
			ps.executeUpdate();
			
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()) {
				id = pk.getInt(1);
			}
			
			r.setRequesterId(requester);
			r.setReason(reason);
			r.setAmount(amount);
			r.setType_Id(rtype);
			r.setStatus_Id(0);
			
			conn.commit();
			return id;
		
		//catch has been tested & works
		} catch (SQLIntegrityConstraintViolationException e) {
    		System.out.println("A reimbursement with that information already exists.");
    		System.out.println("Please try again.");
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int addFullReimbursement(int requester, String reason, double amount, String receipt, int rtype) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			Reimbursement r = new Reimbursement();
			
			String sql = "insert into reimbursement (requester_id, reason, amount, type_id, receipt) values (?,?,?,?,?)";
			String[] key = new String[1];
			key[0] = "reimburse_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, requester);  //it won't matter what is contained within the String
			ps.setString(2, reason);
			ps.setDouble(3, amount);
			ps.setInt(4, rtype);
			ps.setString(5, receipt);
			
			ps.executeUpdate();
			
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()) {
				id = pk.getInt(1);
			}
			
			r.setRequesterId(requester);
			r.setReason(reason);
			r.setAmount(amount);
			r.setReceipt(receipt);
			r.setType_Id(rtype);
			r.setStatus_Id(0);
			
			conn.commit();
			return id;
		
		//catch has been tested & works
		} catch (SQLIntegrityConstraintViolationException e) {
    		System.out.println("A reimbursement with that information already exists.");
    		System.out.println("Please try again.");
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void updateReimbursementStatus(int rid, int stat, int resid) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "update reimbursement set status_id = ?, resolver_id = ? where reimburse_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, stat);  //it won't matter what is contained within the String
			ps.setInt(2, resid);
			ps.setInt(3, rid);
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
	}
	
	public void updateReimbursementNotes(int rid, String notes) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "update reimbursement set resolver_notes = ? where reimbursement_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, notes);  //it won't matter what is contained within the String
			ps.setInt(2, rid);
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
