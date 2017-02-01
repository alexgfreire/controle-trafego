package br.com.controle.model;

import java.io.Serializable;

public class Vehicle implements Serializable, Comparable<Vehicle> {
	
	private static final long serialVersionUID = -7442167807656031869L;
	
	private String name;
	
	private String model;
	
	private Integer passengers;
	
	private boolean utilizada = false;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}
	
	

	public boolean isUtilizada() {
		return utilizada;
	}

	public void setUtilizada(boolean utilizada) {
		this.utilizada = utilizada;
	}

	@Override
	public int compareTo(Vehicle o) {
		return passengers.compareTo(o.getPassengers());
	}

    
}