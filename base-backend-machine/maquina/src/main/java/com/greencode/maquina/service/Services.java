package com.greencode.maquina.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.greencode.maquina.model.Usuario;
import javax.swing.JOptionPane;

@Service
public class Services {

    private final RestTemplate restTemplate;
    private final String url;

    public Services() {
        restTemplate = new RestTemplate();
        url = "http://greencode-backend-lb-170707148.us-east-1.elb.amazonaws.com";
       
    }

    public Usuario login(Usuario user) {
        String urlLogin = url + "/login";
        ResponseEntity<Usuario> resposta = null ;
        try{
            resposta = restTemplate.postForEntity(urlLogin, user, Usuario.class);
        }catch(Exception e){
            return null;
        }
        
        return resposta.getBody();
    }

    public Boolean atualizarPontos(Usuario user, int pontos) {
        String urlAtualizar = url + "/usuario/atualizarPontos/" + pontos;
        
        ResponseEntity<Boolean> resposta = restTemplate.postForEntity(urlAtualizar, user, Boolean.class);
        
        return resposta.getBody();
    }
    
    public Boolean atualizarItens(Usuario user, int itens) {
        String urlAtualizar = url + "/usuario/atualizarItens/" + itens;
        
        ResponseEntity<Boolean> resposta = restTemplate.postForEntity(urlAtualizar, user, Boolean.class);
        
        return resposta.getBody();
    }
    
    public String enviarEmail(String email) {
		String urlEmail = url + "/email/enviar";
		ResponseEntity<String> resposta = restTemplate.postForEntity(urlEmail, email, String.class);
		return resposta.getBody();
	}
}
