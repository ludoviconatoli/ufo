package it.polito.tdp.uf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {
		
	//Vediamo come usare il DAO
	public List<String> readShapes() throws SQLException{
		
		Connection conn = DBConnect.getConnection();
		
		Statement st = conn.createStatement();
		
		String sql = "SELECT DISTINCT shape "
				+ "FROM sighting "
				+ "WHERE shape<>'' "
				+ "ORDER BY shape ASC";
		
		ResultSet res = st.executeQuery(sql);
		
		List<String> formeUFO = new ArrayList<>();
		while(res.next()) {
			String forma = res.getString("shape");//risultato della colonna shape alla riga x
			formeUFO.add(forma);
		}
		
		st.close();
		conn.close();
		return formeUFO;
	}
	
	public int countByShape(String shape) {
        
		try{
			Connection conn = DBConnect.getConnection();
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ? ";
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			
			st2.setString(1, shape);
			ResultSet res2 = st2.executeQuery();
			
			res2.first();
			int count = res2.getInt("cnt");
			
			st2.close();
			conn.close();
			return count;
		    
		}catch(SQLException e) {
			throw new RuntimeException("Database error in countByShape", e);
		}
	}
}
