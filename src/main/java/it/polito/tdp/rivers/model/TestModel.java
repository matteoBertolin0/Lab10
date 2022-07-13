package it.polito.tdp.rivers.model;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();
		m.loadAllRiver();
		m.loadAllFlowsByRiver(m.getRiversMap().get(3));
		m.loadAllFlowsAvg();
//		System.out.println(m.getRiversMap().get(1).getFlows());
		Simulator sim = new Simulator();
		System.out.println("River: " + m.getRiversMap().get(3).getName() + ", k: 2" );
		sim.init(m.getRiversMap().get(3), 10);
		sim.run();

	}

}
