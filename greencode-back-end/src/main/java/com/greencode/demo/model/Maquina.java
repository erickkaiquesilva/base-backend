package com.greencode.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "TB_MAQUINA")
public class Maquina {
	@Id
	@GeneratedValue
	private Long id;

	@JsonProperty
	private String tipoMaquina;

	@JsonProperty
	private int idLocalizacao;

	@JsonProperty
	private String status;

	@JsonProperty
	private int idLocal;

	@JsonProperty
	private int idReciclavel;
	
	@OneToMany(mappedBy = "maquina")
	private List<Reciclados> reciclados;

	public Maquina() {
		super();
	}

	public Maquina(Long id, String tipoMaquina, int idLocalizacao, String status, int idLocal, int idReciclavel) {
		this.id = id;
		this.tipoMaquina = tipoMaquina;
		this.idLocalizacao = idLocalizacao;
		this.status = status;
		this.idLocal = idLocal;
		this.idReciclavel = idReciclavel;
	}
	public Long getId() {
		return id;
	}
	public String getTipoMaquina() {
		return tipoMaquina;
	}
	public int getIdLocalizacao() {
		return idLocalizacao;
	}
	public String getStatus() {
		return status;
	}
	public int getIdLocal() {
		return idLocal;
	}
	public int getIdReciclavel() {
		return idReciclavel;
	}

}
