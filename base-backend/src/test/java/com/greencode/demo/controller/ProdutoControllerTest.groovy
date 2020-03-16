package com.greencode.demo.controller

import static org.junit.Assert.assertEquals

import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import com.greencode.demo.dao.ProdutoRepository
import com.greencode.demo.model.Produto

import groovy.transform.ToString
import spock.lang.Specification

@ToString
class ProdutoControllerTest extends Specification{
	
	def ProdutoController controller;
	def ProdutoRepository tds;
	def List<Produto> produtos = new ArrayList();

	public void setup(){
		tds = Mockito.mock(ProdutoRepository.class);
		controller = new ProdutoController(tds);
		
	}
	
	def "achar Produto Por Nome"(){
		given:
			
			Produto produto = new Produto(1,"arroz",20);
			produtos.add(produto);
			Mockito.when(tds.buscarPorNome("arroz")).thenReturn(produtos);
			ProdutoController controllerP = new ProdutoController(tds);
		
		when:
			ResponseEntity<List<Produto>> resposta = controllerP.buscarPorNome("arroz");

		then:
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(produtos, resposta.getBody());
	}
	
	def "achar Todos Produtos"(){
		given:
			
			Mockito.when(tds.buscarTodos()).thenReturn(produtos);
			ProdutoController controllerP = new ProdutoController(tds);
		
		when:
			ResponseEntity<List<Produto>> resposta = controllerP.buscarTodos();

		then:
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(produtos, resposta.getBody());
	}
	
	def "cadastrar Produto"(){
		given:
			Produto produto = new Produto(1,"arroz",20);
			ProdutoController controllerP = new ProdutoController(tds);
		
		when:
			ResponseEntity<Boolean> resposta = controllerP.cadastrarProduto(produto);
	
		then:
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(true, resposta.getBody());
	}
	
	def "deletar Produto"(){
		given:
			Produto produto = new Produto(1,"arroz",20);
			ProdutoController controllerP = new ProdutoController(tds);
		
		when:
			ResponseEntity<Boolean> resposta = controllerP.deletarProduto(produto);
	
		then:
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(true, resposta.getBody());
	}
	
	def "atualizar Preço Produto"(){
		given:
			Produto produto = new Produto(1,"arroz",20);
			ProdutoController controllerP = new ProdutoController(tds);
		
		when:
			ResponseEntity<Boolean> resposta = controllerP.atualizarPreco(produto);
	
		then:
			assertEquals(HttpStatus.OK, resposta.getStatusCode());
			assertEquals(true, resposta.getBody());
	}
	

}
