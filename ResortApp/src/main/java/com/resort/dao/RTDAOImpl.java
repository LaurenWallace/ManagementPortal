package com.resort.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.resort.pojos.ReimbursementType;
import com.resort.util.ConnectionFactory;

public class RTDAOImpl implements RTDAO{

	@Override
	public HashMap<Integer, String> getRT() {
		
		HashMap<Integer, String> types = new HashMap<Integer, String>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String sql = "select * from reimbursement_type";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()){
				ReimbursementType rtype = new ReimbursementType();
				rtype.settypeId(rs.getInt(1));
				rtype.setName(rs.getString(2));
				types.put(rtype.gettypeId(), rtype.getName());
			}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return types;
}

}
