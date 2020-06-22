package com.greencode.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Random;

import com.greencode.email.dao.UsuariosRepository;
import com.greencode.email.gerarsenha.GerarSenha;
import com.greencode.email.model.Usuario;

@RestController
public class EmailController {
	
	private UsuariosRepository tds;
	
    private JavaMailSender javaMailSender;	
	
	@Autowired
	public EmailController(UsuariosRepository todos, JavaMailSender javaMailSender) {
		tds = todos;
		this.javaMailSender = javaMailSender;
	}
	
	@PostMapping("/email/enviar")
	public ResponseEntity<String> enviarNovaSenha(@RequestBody String email){
		
		Random gerador = new Random();
		Usuario usuario = tds.buscarEmail(email);
		GerarSenha gs = new GerarSenha();
		String fatorial = gs.fatorial(gerador.nextInt(20) + 13)+"";
		for(int i = 0; i < 10; i++) {
			gs.getPilha().add(fatorial.charAt(i));
		}
		
		
		if(usuario != null) {
			String senha = gs.exibeSenha(gerador.nextBoolean());
			tds.atualizarSenha(senha, usuario.getId());
			
			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(email);
	
	        msg.setSubject("Sua Nova Senha");
	        msg.setText("Nova Senha: " + senha);
	
	        javaMailSender.send(msg);
			
			return ResponseEntity.ok("Senha Alterada, Verifique seu Email");
		}
		
		return ResponseEntity.ok("Email não cadastrado");
	}
	
	
	@PostMapping("/email/cupon")
	public ResponseEntity<String> enviarNovoCupon(@RequestBody String email ){
		
		Random gerador = new Random();
		Usuario usuario = tds.buscarEmail(email);
		GerarSenha gs = new GerarSenha();
		String fatorial = gs.fatorial(gerador.nextInt(20) + 13)+"";
		for(int i = 0; i < 10; i++) {
			gs.getPilha().add(fatorial.charAt(i));
		}
		
		
		if(usuario != null) {
			String cupon = gs.exibeSenha(gerador.nextBoolean());
			
			SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(email);
	
	        msg.setSubject("Cupon Adquirido");
	        msg.setText("Cupon: " + cupon.toUpperCase());
	
	        javaMailSender.send(msg);
			
			return ResponseEntity.ok("Cupon Enviado, Verifique seu Email");
		}
		
		return ResponseEntity.ok("Email não cadastrado");
	}
}
