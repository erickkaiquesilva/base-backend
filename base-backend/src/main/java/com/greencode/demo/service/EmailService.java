package com.greencode.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EmailService {
	private final RestTemplate restTemplate;
	private final String url;
	
	public EmailService() {
		restTemplate = new RestTemplate();
		url = "http://localhost:8080/email";
	}
	
	public String mudarSenha(String email) {
		String urlCadastro = url + "/enviar";
		ResponseEntity<String> resposta = restTemplate.postForEntity(urlCadastro, email, String.class);
		return resposta.getBody();
	}
	
	public String enviarCupon(String email) {
		String urlCadastro = url + "/cupon";
		ResponseEntity<String> resposta = restTemplate.postForEntity(urlCadastro, email, String.class);
		return resposta.getBody();
	}
}
