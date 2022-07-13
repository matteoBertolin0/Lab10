package it.polito.tdp.rivers.model;

public class Bacino {
	private double capacitaMax;
	private double quantita;
	
	public Bacino(double capacitaMax, double quantita) {
		super();
		this.capacitaMax = capacitaMax;
		this.quantita = quantita;
	}
	public double getCapacitaMax() {
		return capacitaMax;
	}
	public void setCapacitaMax(double capacitaMax) {
		this.capacitaMax = capacitaMax;
	}
	public double getQuantita() {
		return quantita;
	}
	public void incrementQuantita(double quantita) {
		this.quantita += quantita;
	}
	@Override
	public String toString() {
		return "Bacino [capacitaMax=" + capacitaMax + ", quantita=" + quantita + "]";
	}
	
	
}
