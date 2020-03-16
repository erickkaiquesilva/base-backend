package com.greencode.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "colReciclavel")
public class Reciclavel {

	@Id
	private String id;

	private int peso;

	private int pontos;

	private String tipo;

	public Reciclavel() {
	}

	public Reciclavel(int peso, int pontos, String idTipo) {
		this.peso = peso;
		this.pontos = pontos;
		this.tipo = idTipo;
	}

	public String getId() {
		return id;
	}

	public int getPeso() {
		return peso;
	}

	public int getPontos() {
		return pontos;
	}

	public String getTipo() {
		return tipo;
	}
}
