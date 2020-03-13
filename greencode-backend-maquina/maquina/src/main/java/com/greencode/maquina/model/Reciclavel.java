package com.greencode.maquina.model;

import java.util.HashSet;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonProperty;

public class Reciclavel {
	private Long id;

	@JsonProperty
	private int peso;

	@JsonProperty
	private int pontos;

	@JsonProperty
	private int idTipo;
	

	//@ManyToOne
	//@JoinColumn(name="tipo_id")
	//private Tipo tipo;
	
	//@ManyToMany(fetch = FetchType.LAZY,
    //        cascade = {
    //            CascadeType.PERSIST,
    //            CascadeType.MERGE
    //        },
    //        mappedBy = "reciclavel")
    //private Set<Reciclados> reciclados = new HashSet<>();

	public Reciclavel() {}

	public Reciclavel(Long id, int peso, int pontos, int idTipo) {
		this.id = id;
		this.peso = peso;
		this.pontos = pontos;
		this.idTipo = idTipo;
	}
	public Long getId() {
		return id;
	}

	public int getPeso() {
		return peso;
	}
	public int getPontos() {
		return pontos;
	}
	public int getIdTipo() {
		return idTipo;
	}
}
