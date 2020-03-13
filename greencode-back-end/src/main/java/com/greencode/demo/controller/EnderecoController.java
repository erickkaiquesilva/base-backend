package com.greencode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greencode.demo.dao.EnderecoRepository;
import com.greencode.demo.model.Endereco;

@RestController
public class EnderecoController {
	
	private EnderecoRepository tdEnderecos;
	
	@Autowired
	public EnderecoController(EnderecoRepository enderecos) {
		tdEnderecos = enderecos;
	}
	
	@PostMapping("/endereco/criar")
	public ResponseEntity<Boolean> cadastrarEndereco(@RequestBody Endereco endereco){
		
		tdEnderecos.save(endereco);
		
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/endereco/deletar")
	public ResponseEntity<Boolean> deletarEndereco(@RequestBody Endereco endereco){
		
		tdEnderecos.delete(endereco);
		
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/endereco/buscarPorCidade/{cidade}")
	public ResponseEntity<List<Endereco>> buscarPorCidade(@PathVariable String cidade){
		
		List<Endereco> enderecosAchados = tdEnderecos.buscarPorCidade(cidade);
		
		return ResponseEntity.ok(enderecosAchados);
	}
	
	@PostMapping("/endereco/atualizarNumero")
	public ResponseEntity<Boolean> atualizarNumero(@RequestBody Endereco endereco){
		tdEnderecos.atualizarNumero(endereco.getNumero(), endereco.getIdLocal());
		
		
		return ResponseEntity.ok(true);
	}
	
}
