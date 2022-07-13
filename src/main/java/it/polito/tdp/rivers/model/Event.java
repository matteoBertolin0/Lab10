package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{
	
	private LocalDate time;
	private double fIn;
	
	public Event(LocalDate time, double fIn) {
		super();
		this.time = time;
		this.fIn = fIn;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	public double getfIn() {
		return fIn;
	}

	public void setfIn(double fIn) {
		this.fIn = fIn;
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", fIn=" + fIn + "]";
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.getTime());
	}
	
	
	
}
