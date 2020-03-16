package com.greencode.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "TB_TIPO")
public class Tipo {
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty
	private String tipo;
	
	@OneToMany(mappedBy = "tipo")
	private List<Reciclavel> reciclaveis;

	public Tipo() {
		super();
	}
	public Tipo(Long id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}
	public Long getId() {
		return id;
	}
	public String getTipo() {
		return tipo;
	}

}
