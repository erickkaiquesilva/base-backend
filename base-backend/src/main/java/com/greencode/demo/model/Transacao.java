package com.greencode.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="TB_TRANSACAO")
public class Transacao {
	
	@Id
	@GeneratedValue
	private long id;
	
	@JsonProperty
	private LocalDateTime data_hora;
	
	@JsonProperty
	private int valor_transacao;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "Produtos_transacao",
                joinColumns = { @JoinColumn(name = "transacao_id") },
                inverseJoinColumns = { @JoinColumn(name = "produtos_id") })
	private Set<Produto> produtos = new HashSet<>();
	
	public Transacao(long id, LocalDateTime data_hora, int valor_transacao, Usuario usuario, HashSet<Produto> produtos) {
		this.id = id;
		this.data_hora = data_hora;
		this.valor_transacao = valor_transacao;
		this.usuario = usuario;
		this.produtos = produtos;
	}
	
	public Transacao(LocalDateTime data_hora, int valor_transacao, Usuario usuario) {
		this.data_hora = data_hora;
		this.valor_transacao = valor_transacao;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Transacao [data_hora=" + data_hora + ", valor_transacao=" + valor_transacao + ", usuario=" + usuario
				+ ", produtos=" + produtos + "]";
	}

	public Transacao() {
		
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getData_hora() {
		return data_hora;
	}

	public int getValor_transacao() {
		return valor_transacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public Set<Produto> getProdutos() {
		return produtos;
	}
	
	
	
	
}
