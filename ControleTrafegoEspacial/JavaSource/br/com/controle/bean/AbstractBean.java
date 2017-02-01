package br.com.controle.bean; 

import java.io.Serializable;

public abstract class AbstractBean implements Serializable { 

	private Integer id; 

	public Integer getId() { 
		return id; 
	} 

	public void setId(Integer id) { 
		this.id = id; 
	} 

	@Override 
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		result = prime * result + ((id == null) ? 0 : id.hashCode()); 
		return result; 
	} 

	@Override 
	public boolean equals(Object obj) { 
		if (this == obj) 
			return true; 
		if (obj == null) 
			return false; 
		if (getClass() != obj.getClass()) 
			return false; 

		return (obj instanceof AbstractBean) ? (this.getId() == null ? this == obj 
				: this.getId().equals(((AbstractBean) obj).getId())) 
				: false; 
	} 

} 