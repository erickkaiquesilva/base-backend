package com.greencode.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "TB_Reciclados")
public class Reciclados {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty
	private LocalDateTime data_hora;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="maquina_id")
	private Maquina maquina;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
        @JoinTable(name = "Reciclaveis_Reciclados",
                joinColumns = { @JoinColumn(name = "reciclados_id") },
                inverseJoinColumns = { @JoinColumn(name = "reciclavel_id") })
	private Set<Reciclavel> reciclavel = new HashSet<>();
	
	
}
