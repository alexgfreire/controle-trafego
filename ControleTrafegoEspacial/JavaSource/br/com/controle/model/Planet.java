package br.com.controle.model;

import java.io.Serializable;

import br.com.controle.bean.AbstractBean;

public class Planet extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 7617431227721780801L;
	
	private String name;
	
	private String diameter;
	
	private String population;
	
	private String climate;
	
	private String terrain;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

}