package com.greencode.demo.controller;

import static org.junit.Assert.assertEquals;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.greencode.demo.dao.UsuariosRepository;
import com.greencode.demo.model.Usuario;

@SuppressWarnings("deprecation")
public class LoginControllerTest {
	
	private LoginController controller;
	
	private UsuariosRepository tds;
	
	@Before
	public void setUP() {
		tds = Mockito.mock(UsuariosRepository.class);
		controller = new LoginController(tds);
	}

	@Test
	public void loginSucesso() {
		Usuario user = new Usuario("admin","admin");
		Mockito.when(tds.logar("admin","admin")).thenReturn(user);
		LoginController controller = new LoginController(tds);
		ResponseEntity<Usuario> resposta = controller.validarLogin(user);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(user, resposta.getBody());
	}

	@Test
	public void loginComFalha() {

		LoginController controller = new LoginController(tds);

		ResponseEntity<Usuario> resposta = controller.validarLogin(new Usuario("login", "senha"));

		assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());

	}
	
	
	

}
