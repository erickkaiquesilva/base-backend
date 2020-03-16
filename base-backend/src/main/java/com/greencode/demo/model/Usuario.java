package com.greencode.demo.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="TB_USUARIO",uniqueConstraints={@UniqueConstraint(columnNames="email")})
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty
	private String nome;
	
	@JsonProperty
	private String sobrenome;
	
	@JsonProperty
	private String senha;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String cpf;
	
	@JsonProperty
	private int pontos;
	
	@JsonProperty
	private boolean admin;
	
	@JsonProperty
	private int totalItens;
	
	@OneToMany(mappedBy = "usuario")
	private List<Transacao> transacao;
	
	@OneToMany(mappedBy = "usuario")
	private List<Reciclados> reciclados;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String senha, String email) {
		this.senha = senha;
		this.email = email;
	}
	
	public Usuario(int itens, int pontos) {
		this.totalItens = itens;
		this.pontos = pontos;
	}
	
	public Usuario(Long id, String nome, String senha,String sobrenome, String email, String cpf, boolean admin, int totalItens) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.cpf = cpf;
		this.sobrenome = sobrenome;
		this.admin = admin;
		this.totalItens = totalItens;
		
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	public String getEmail() {
		return email;
	}
	public String getCpf() {
		return cpf;
	}
	public int getPontos() {
		return pontos;
	}
	public String getSobrenome() {
		return sobrenome;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", senha=" + senha + ", email="
				+ email + ", cpf=" + cpf + ", pontos=" + pontos + "]";
	}
	
	
}

