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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
		
		//mexi aqui
		
        String to = email;
        String from = "dont.reply.greencode@gmail.com";
        Properties properties = System.getProperties();
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "Green123Code");

            }

        });
		
		//pare as variaveis aqui
		
		
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
			
			  try {
		            // Create a default MimeMessage object.
		            MimeMessage message = new MimeMessage(session);

		            // Set From: header field of the header.
		            message.setFrom(new InternetAddress(from));

		            // Set To: header field of the header.
		            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		            // Set Subject: header field
		            message.setSubject("Sua nova senha de acesso da Green Code");

		            // Now set the actual message
		            message.setText(senha);

		            System.out.println("enviando...");
		            // Send message
		            Transport.send(message);
		            System.out.println("Mensagem enviada com exito....");
		        } catch (MessagingException mex) {
		            mex.printStackTrace();
		        }
			
//			String senha = gs.exibeSenha(gerador.nextBoolean());
//			tds.atualizarSenha(senha, usuario.getId());
//			
//			SimpleMailMessage msg = new SimpleMailMessage();
//	        msg.setTo(email);
//	
//	        msg.setSubject("Sua Nova Senha");
//	        msg.setText("Nova Senha: " + senha);
//	
//	        javaMailSender.send(msg);
			
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
