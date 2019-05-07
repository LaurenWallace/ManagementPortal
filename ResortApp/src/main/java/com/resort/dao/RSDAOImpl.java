package com.resort.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.resort.pojos.ReimbursementStatus;
import com.resort.util.ConnectionFactory;

public class RSDAOImpl implements RSDAO {

	@Override
	public HashMap<Integer, String> getRS() {
		
		HashMap<Integer, String> stats = new HashMap<Integer, String>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String sql = "select * from reimbursement_status";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()){
				ReimbursementStatus rstat = new ReimbursementStatus();
				rstat.setStatId(rs.getInt(1));
				rstat.setName(rs.getString(2));
				stats.put(rstat.getStatId(), rstat.getName());
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return stats;
}

}
