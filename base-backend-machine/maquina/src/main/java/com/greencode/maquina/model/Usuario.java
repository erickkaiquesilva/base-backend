package com.greencode.maquina.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

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

    public Usuario() {
        super();
    }

    public int getTotalItens() {
		return totalItens;
	}

	public Usuario(String senha, String email) {
        this.senha = senha;
        this.email = email;
    }

    public Usuario(int itens, int pontos) {
        this.totalItens = itens;
        this.pontos = pontos;
    }

    public Usuario(Long id, String nome, String senha, String sobrenome, String email, String cpf, boolean admin, int totalItens) {
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
