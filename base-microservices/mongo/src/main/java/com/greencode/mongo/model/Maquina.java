package com.greencode.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "colMaquina")
public class Maquina {
	
	@Id
	private String id;

	private String local;
	
	private String status;
	
	private String nivel;

	public Maquina() {}

	public Maquina( String local,String status, String nivel) {
		this.status = status;
		this.local = local;
		this.nivel = nivel;
		
	}
	public String getId() {
		return id;
	}
	public String getStatus() {
		return status;
	}
	public String getLocal() {
		return local;
	}
	public String getNivel() {
		return nivel;
	}
}
