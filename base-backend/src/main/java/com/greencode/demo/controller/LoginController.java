package com.greencode.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.greencode.demo.accesslist.AccessList;
import com.greencode.demo.accesslist.CreateFile;
import com.greencode.demo.dao.UsuariosRepository;
import com.greencode.demo.model.Usuario;

@RestController
public class LoginController {
	
	private UsuariosRepository tdUsuario;
	AccessList<String> lista = new AccessList<String>(1);
 	
	@Autowired
	public LoginController(UsuariosRepository usuarios) {
		tdUsuario = usuarios;
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> validarLogin(@RequestBody Usuario usuario) {
		Usuario usuarioLogado = tdUsuario.logar(usuario.getEmail(), usuario.getSenha());
		
		if(usuarioLogado != null) {
			lista.adiciona(usuario.getEmail());
			if(lista.getTamanho() == 1) {
			System.out.println(lista);
				//new CreateFile().gravar(lista);
				lista.limpa();
			}
			return ResponseEntity.ok(usuarioLogado);			
		}
	
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if(session.getAttribute("usuarioLogado") != null){
			session.invalidate();
		    return "deslogado com sucesso";
		}
		
		return "já foi deslogado";
	}
	
	
}
