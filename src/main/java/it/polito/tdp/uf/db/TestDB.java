package it.polito.tdp.uf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDB {

	public static void main(String[] args) throws SQLException{
		
		/*String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=1999";
		
		//Connection
		//import tutti da java.sql occhio!!!
		try {
			//Connection è un'interfaccia
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			//Nuova lettera per inviare una query
			//è un'interfaccia
			Statement st = conn.createStatement();
			
			//Copio e incollo da HeidiSql
			//Metto uno spazio al fondo di ogni riga tranne all'ultima
			//La query chiede la colonna che si chiama shape di una tabella
			String sql = "SELECT DISTINCT shape "
					+ "FROM sighting "
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC";
			
			//Faccio eseguire la query
			//è un'interfaccia che esegue le SELECT
			//per INSERT, UPDATE si usa int executeUpdate(String sql)
			ResultSet res = st.executeQuery(sql);
			
			List<String> formeUFO = new ArrayList<>();
			while(res.next()) {
				String forma = res.getString("shape");//risultato della colonna shape alla riga x
				formeUFO.add(forma);
			}
			
			st.close();
			System.out.println(formeUFO);
			
			
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting WHERE shape = ? ";
			String shapeScelta = "circle";
			
			//La preparedStatement permette di stabilire una query non indicando direttamente un valore
			//? -> valore indeterminato
			PreparedStatement st2 = conn.prepareStatement(sql2);
			//imposto nel punto di domanda sopra shapeScelta
			st2.setString(1, shapeScelta);
			ResultSet res2 = st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			
			st2.close();
			
			System.out.println("UFO di forma "+ shapeScelta + " sono: " + count);
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		*/
		
		SightingDAO dao = new SightingDAO();
		
		List<String> formeUFO = dao.readShapes();
		for(String forma: formeUFO) {
			int count = dao.countByShape(forma);
			System.out.println("Ufo di forma " + forma + " sono: " + count);
		}
	}

}
