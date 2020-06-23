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
	public ResponseEntity<String> enviarNovaSenha(@RequestBody String email) {

		// mexi aqui

		//String to = email;
		//String from = "dont.reply.greencode@gmail.com";
		//Properties properties = System.getProperties();

		//Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			//protected PasswordAuthentication getPasswordAuthentication() {

				//return new PasswordAuthentication(from, "jyzenfqinviebrmn");

			//}

		//});

		// pare as variaveis aqui

		Random gerador = new Random();
		Usuario usuario = tds.buscarEmail(email);
		GerarSenha gs = new GerarSenha();
		String fatorial = gs.fatorial(gerador.nextInt(20) + 13) + "";
		for (int i = 0; i < 9; i++) {
			gs.getPilha().add(fatorial.charAt(i));
		}

		if (usuario != null) {

			String senha = gs.exibeSenha(gerador.nextBoolean());
			tds.atualizarSenha(senha, usuario.getId());

			try {
				
				SimpleMailMessage msg = new SimpleMailMessage();
		        msg.setTo(email);
		
		        msg.setSubject("Sua Nova Senha");
		        msg.setText("Parabéns você trocou sua senha com sucesso\n"
						+ "Utilizze essa senha para fazer login no App ou no site da Green Code"
						+ "A Green Code agradece sua ajuda :)\n\n"
						+ "Nova senha: " + senha);
		
		        javaMailSender.send(msg);

				System.out.println("enviando...");
				System.out.println("Mensagem enviada com exito....");
			} catch (Exception mex) {
				mex.printStackTrace();
			}
			return ResponseEntity.ok("Senha Alterada, Verifique seu Email");
		}

		return ResponseEntity.ok("Email não cadastrado");
	}

//			

	@SuppressWarnings("unused")
	@PostMapping("/email/cupon")
	public ResponseEntity<String> enviarNovoCupon(@RequestBody String email) {

		System.out.println("Chegou aqui antes de gerar o cupon");
		Random gerador = new Random();
	
		Usuario usuario = tds.buscarEmail(email);
		GerarSenha gs = new GerarSenha();
		String fatorial = gs.fatorial(gerador.nextInt(20) + 13) + "";
		for (int i = 0; i < 9; i++) {
			gs.getPilha().add(fatorial.charAt(i));
		}
				
		
		System.out.println("depois de gerar o cupon");
		System.out.println(email);


		if (usuario != null) {

			System.out.println("entrou no if se tem usuario");
			
			String cupom = gs.exibeSenha(gerador.nextBoolean());

			try {
				
					SimpleMailMessage msg = new SimpleMailMessage();
					msg.setTo(email);
				
					msg.setSubject("Cupon Adquirido");
					msg.setText("Parabéns você trocou seus pontos com sucesso\n"
							+ "Agora pegue seu cupom e garanta seu desconto na loja parceira\n\n"
							+ "A Green Code agradece sua ajuda :)\n\n"
							+ "Cupon: " + "GREEN" + cupom.toUpperCase());
				
					System.out.println("enviando...");
					
					javaMailSender.send(msg);
				
				System.out.println("Mensagem enviada com exito....");
			} catch (Exception mex) {
				mex.printStackTrace();
			}
			return ResponseEntity.ok("Cupon Enviado, Verifique seu Email");
		}

		System.out.println("email não cadastrado");
		return ResponseEntity.ok("Email não cadastrado");
	}
}
