package com.greencode.demo.controller

import static org.junit.Assert.assertEquals

import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import com.greencode.demo.dao.TransacaoRepository
import com.greencode.demo.dao.UsuariosRepository
import com.greencode.demo.model.Produto
import com.greencode.demo.model.Usuario

import groovy.transform.ToString
import spock.lang.Specification

@ToString
class UsuarioControllerTest extends Specification {

	def Produto produto;
	def UsuarioController controller;
	def TransacaoRepository tdsTransacao;
	def UsuariosRepository tds;
	
	
	public void setup(){
		tds = Mockito.mock(UsuariosRepository.class);
		tdsTransacao = Mockito.mock(TransacaoRepository.class);
		controller = new UsuarioController(tds,tdsTransacao);
	}
	
	def "deve Gastar"(){
		given:
		Long id = 1;
		Usuario usuario = new Usuario(id,"nome","senha","sobrenome","email","cpf");
		Mockito.when(tds.buscarPontosPorId(id)).thenReturn(10000);
		Produto produto = new Produto(1,"nome",20);
		List<Produto> produtos = new ArrayList();
		
		
	when:
		ResponseEntity<Boolean> resposta = controller.gastar(produtos,usuario);

	then:
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(true, resposta.getBody());
	}
	
	def "não Deve Gastar"(){
		given:
		Long id = 1;
		Usuario usuario = new Usuario(id,"nome","senha","sobrenome","email","cpf");
		Mockito.when(tds.buscarPontosPorId(id)).thenReturn(0);
		Produto produto = new Produto(1,"nome",20);
		List<Produto> produtos = new ArrayList();
		
		
		when:
		ResponseEntity<Boolean> resposta = controller.gastar(produtos,usuario);

		then:
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(false, resposta.getBody());
	}
	
	def "não Deve Gastar2"(){
		given:
		Long id = 1;
		Usuario usuario =null;
		Mockito.when(tds.buscarPontosPorId(id)).thenReturn(0);
		Produto produto = new Produto(1,"nome",20);
		List<Produto> produtos = new ArrayList();
		
		
		when:
		ResponseEntity<Boolean> resposta = controller.gastar(produtos,usuario);

		then:
		assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
	}
	
	def "cadastrar"(){
		given:
		Long id = 1;
		Usuario usuario = new Usuario(id,"nome","senha","sobrenome","email","cpf");
		Mockito.when(tds.buscarEmail("email")).thenReturn(null);
		
		when:
		ResponseEntity<Boolean> resposta = controller.cadastrarUsuario(usuario);

		then:
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuario, resposta.getBody());
	}
	
	def "não cadastrar"(){
		given:
		Long id = 1;
		Usuario usuario = new Usuario(id,"nome","senha","sobrenome","email","cpf");
		Mockito.when(tds.buscarEmail("email")).thenReturn(usuario);
		
		when:
		ResponseEntity<Boolean> resposta = controller.cadastrarUsuario(usuario);

		then:
		assertEquals(HttpStatus.CONFLICT, resposta.getStatusCode());
		
	}
	
	def "buscar Pontos"(){
		given:
		Long id = 1;
		Usuario usuario = new Usuario(id,"nome","senha","sobrenome","email","cpf");
		Mockito.when(tds.buscarPontosPorId(id)).thenReturn(300);
		
		when:
		ResponseEntity<Boolean> resposta = controller.buscarPontos(usuario);

		then:
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(300, resposta.getBody());
	}
	
	def "não Buscar Pontos"(){
		given:
		Long id = 1;
		Usuario usuario = null;
		Mockito.when(tds.buscarPontosPorId(id)).thenReturn(0);
		
		when:
		ResponseEntity<Boolean> resposta = controller.buscarPontos(usuario);

		then:
		assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
		
	}
	
	def "deve mudar senha"(){
		given:
		Long id = 1;
		Usuario usuario = new Usuario(id,"nome","senha","sobrenome","email","cpf");
		
		when:
		ResponseEntity<String> resposta = controller.mudarSenha(usuario,"novaSenha");

		then:
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals("Senha Alterada", resposta.getBody());
	}
	
	def "não deve mudar senha"(){
		given:
		Long id = 1;
		Usuario usuario = null;
		
		when:
		ResponseEntity<String> resposta = controller.mudarSenha(usuario,"novaSenha");

		then:
		assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());

	}
	
}
