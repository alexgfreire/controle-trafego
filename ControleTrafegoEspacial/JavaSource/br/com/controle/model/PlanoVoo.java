package br.com.controle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlanoVoo implements Serializable {
	
	private static final long serialVersionUID = -2903277061069603602L;

	private String id;
	
	private List<People> pessoa = new ArrayList<People>();
	
	private Planet planetaDestino;
	
	private Vehicle aeronave;


	public List<People> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<People> pessoa) {
		this.pessoa = pessoa;
	}

	public Planet getPlanetaDestino() {
		return planetaDestino;
	}

	public void setPlanetaDestino(Planet planetaDestino) {
		this.planetaDestino = planetaDestino;
	}

	public Vehicle getAeronave() {
		return aeronave;
	}

	public void setAeronave(Vehicle aeronave) {
		this.aeronave = aeronave;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
