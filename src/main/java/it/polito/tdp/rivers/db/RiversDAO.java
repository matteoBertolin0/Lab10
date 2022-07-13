package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public void getAllRivers(Map<Integer, River> idMap) {
		
		final String sql = "SELECT id, name FROM river";


		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next() ) {
				if(!idMap.containsKey(res.getInt("id")))
					idMap.put(res.getInt("id"),new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public void getAllFlowByRiver(River r, Map<Integer, River> idMap){
		final String sql = "SELECT f.`day`,f.flow,f.river "
				+ "FROM flow AS f "
				+ "WHERE f.river=? "
				+ "ORDER BY f.`day`";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(idMap.containsKey(r.getId()))
					idMap.get(res.getInt("river")).getFlows().add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow")));
				else
					System.out.println("Fiume non esistente");
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}

	public void getAllFlowsAvg(Map<Integer, River> idMap) {
		final String sql = "SELECT f.river,AVG(f.flow) AS flowAvg "
				+ "FROM flow AS f "
				+ "GROUP BY f.river";


		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next() ) {
				if(idMap.containsKey(res.getInt("river")))
					idMap.get(res.getInt("river")).setFlowAvg(res.getDouble("flowAvg"));;
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}
}
