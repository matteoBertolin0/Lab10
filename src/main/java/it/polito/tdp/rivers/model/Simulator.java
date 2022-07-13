package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;



public class Simulator {

//	Coda eventi
	private PriorityQueue<Event> queue;
	
//	Parametri di simulazione
	private double F_OUT_MIN;
	private double Q_MAX;
	private double C_TOT;
	private int N_PASSI;
	
//	Output della simulazione
	private int nGiorniInsufficiente;
	private double cAvg;
	
//	Stato del mondo simulato
	Bacino bacino;
	
	public void init(River r, int k) {
		this.Q_MAX = k * r.getFlowAvg() * 24 * 3600 * 30;
		this.F_OUT_MIN = 0.8 * r.getFlowAvg() * 24 * 3600;
		this.nGiorniInsufficiente = 0;
		this.C_TOT = this.Q_MAX/2;
		this.bacino = new Bacino(this.Q_MAX, this.Q_MAX/2);
		this.queue = new PriorityQueue<Event>();
		
		creaEventi(r);
	}

	private void creaEventi(River r) {
		for(Flow f : r.getFlows()) {
			this.queue.add(new Event(f.getDay(),f.getFlow() * 24 * 3600));
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
			this.C_TOT += this.bacino.getQuantita();
			this.N_PASSI++;
		}
		System.out.println("Simuazione effettuata con: "+this.bacino);
		System.out.println("Numero giorni in cui non si è garantita erogazione minima: " + this.nGiorniInsufficiente);
		System.out.println("capacità media del bacino: " + this.C_TOT/this.N_PASSI);
		
	}

	private void processEvent(Event e) {
		int c = (int) (Math.random()*100);
		double flowOut = this.F_OUT_MIN;
		if(0<=c && c<=5) {
			flowOut *= 10;
		}
		if(e.getfIn()>=flowOut) {
			if(this.bacino.getQuantita()+(e.getfIn()-flowOut)<=this.bacino.getCapacitaMax())
				this.bacino.incrementQuantita(e.getfIn()-flowOut);
			
		}else {
			if(this.bacino.getQuantita()>=flowOut) {
				this.bacino.incrementQuantita(e.getfIn()-flowOut);
			}else {
//				this.bacino.incrementQuantita(this.bacino.getCapacitaMax()-this.bacino.getQuantita());
				this.bacino.incrementQuantita(-this.bacino.getQuantita());
				this.nGiorniInsufficiente++;
			}
			
		}
//		System.out.println(e+" flowOut: "+flowOut+"\n"+this.bacino);
	}
	
}
