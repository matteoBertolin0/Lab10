package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private Map<Integer,River> riversMap;
	private RiversDAO dao = new RiversDAO();
	
	public Model() {
		this.riversMap = new HashMap<Integer,River>();
	}
	
	public void loadAllRiver() {
		dao.getAllRivers(riversMap);
	}
	
	public void loadAllFlowsByRiver(River river) {
		dao.getAllFlowByRiver(river, riversMap);
	}
	
	public void loadAllFlowsAvg() {
		dao.getAllFlowsAvg(riversMap);
	}
	
	public List<River> getAllRivers(){
		this.loadAllRiver();
		List<River> rivers = new ArrayList<River>(this.riversMap.values());
		Collections.sort(rivers);
		return rivers;
	}
	
	public Map<Integer, River> getRiversMap(){
		return this.riversMap;
	}
	
}
