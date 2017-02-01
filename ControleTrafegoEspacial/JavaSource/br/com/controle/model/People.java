package br.com.controle.model;

import java.io.Serializable;

import br.com.controle.bean.AbstractBean;

public class People extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 8236567038218371214L;

	private String name;

	private String gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}