package it.polito.tdp.spellchecker.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParolaDAO {
	
	//OSS: la definisco qui perchè è uguale per ogni metodo
	private String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";
	
	public List<String> loadDictionary(){
		
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			
			String sql = "select nome from  parola;";
			ResultSet res = st.executeQuery(sql);
			List<String> diz= new ArrayList<String>();
			while (res.next()) {
				diz.add(res.getString("nome"));
			}
			res.close();
			conn.close();
			return diz;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
	
	
	public boolean contains(String el){
	
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			Statement st = conn.createStatement();
			
			String sql = "select id from  parola where nome=\""+el+"\"";
			
			ResultSet res = st.executeQuery(sql);
			
			//ora uso l' IF perchè ho al più una riga
			if (res.next()) {
				// found
				
				//prima libero la memoria...
				res.close();
				conn.close();

				//...e poi faccio return
				return true;
			} else {
				// not found
				res.close();
				conn.close();
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false; //dopo il catch devo sempre metterci un return
	}

}
