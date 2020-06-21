package com.greencode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greencode.demo.service.EmailService;


@RestController
public class EmailController {

	private final EmailService service;
	
	@Autowired
	public EmailController(EmailService emailService) {
		this.service = emailService;
	}
	
	@PostMapping("/email/enviar")
	public ResponseEntity<String> enviarNovaSenha(@RequestBody String email){
		
		String resposta = service.mudarSenha(email);
		
		return ResponseEntity.ok(resposta);
	}
	
	@PostMapping("/email/cupon")
	public ResponseEntity<String> enviarNovoCupon(@RequestBody String email){
		
		String resposta = service.enviarCupon(email);
		
		return ResponseEntity.ok(resposta);
	}
	
}
