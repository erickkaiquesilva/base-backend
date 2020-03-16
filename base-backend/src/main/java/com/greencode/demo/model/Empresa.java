package com.greencode.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Empresa {
	
	@Id
	@GeneratedValue
	private Long idEmpresa;
	
	@JsonProperty
	private String nome;
	
	@JsonProperty
	private Integer cnpj;

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCNPJ() {
		return cnpj;
	}

	public void setCNPJ(Integer cNPJ) {
		cnpj = cNPJ;
	}
	
	
}
