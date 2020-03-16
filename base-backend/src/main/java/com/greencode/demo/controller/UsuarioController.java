package com.greencode.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greencode.demo.dao.TransacaoRepository;
import com.greencode.demo.dao.UsuariosRepository;
import com.greencode.demo.model.Produto;
import com.greencode.demo.model.Transacao;
import com.greencode.demo.model.Usuario;

@RestController
public class UsuarioController {

	private UsuariosRepository tds;
	private TransacaoRepository transacoes;
	LocalDateTime dataHora;
	
	@Autowired
	public UsuarioController(UsuariosRepository todos, TransacaoRepository transacoes) {
		tds = todos;
		this.transacoes = transacoes;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
		
		if(tds.buscarEmail(usuario.getEmail()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		tds.save(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/usuario/pontos")
	public ResponseEntity<Usuario> buscarPontos(@RequestBody Usuario usuario){
		
		if(usuario != null) {
			int pontos = tds.buscarPontosPorId(usuario.getId());
			int itens = tds.buscarItensPorId(usuario.getId());
			
			Usuario usr = new Usuario(itens,pontos);
			
			return ResponseEntity.ok(usr);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	} 
	
	@PostMapping("/usuario/itens")
	public ResponseEntity<Integer> buscarItens(@RequestBody Usuario usuario){
		
		if(usuario != null) {
			int itens = tds.buscarItensPorId(usuario.getId());
			return ResponseEntity.ok(itens);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@PostMapping("/usuario/mudarSenha/{senha}")
	public ResponseEntity<String> mudarSenha(@RequestBody Usuario usuario, @PathVariable("senha") String novaSenha){
	
		if(usuario != null) {
			tds.atualizarSenha(novaSenha,usuario.getId());
			return ResponseEntity.ok("Senha Alterada");
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	
	
	@PostMapping("/usuario/atualizarPontos/{pontos}")
	public ResponseEntity<Boolean> atualizaPontos(@RequestBody Usuario usuario, @PathVariable int pontos){
		
		if(usuario != null) {
			Long usuarioId = usuario.getId();
			tds.atualizarPontos(pontos, usuarioId);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
		
	}
	
	@PutMapping("/usuario/atualizarItens/{itens}")
	public ResponseEntity<Boolean> atualizarItens(@RequestBody Usuario usuario, @PathVariable int itens){
		
		if(usuario != null) {
			Long usuarioId = usuario.getId();
			tds.atualizarItens(itens, usuarioId);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
		
	}
	
	
	@PostMapping("/usuario/gastar")
	public ResponseEntity<Boolean> gastar(@RequestBody List<Produto> produtos, @RequestBody Usuario usuario){

		if(usuario != null) {
			Long usuarioId = usuario.getId();
			int pontosAtuais = tds.buscarPontosPorId(usuarioId);
			int pontosAtualizados;
			int total = 0;
			dataHora = LocalDateTime.now();
			
			for(Produto p : produtos) {
				total += p.getPreco();
			}
			
			if(pontosAtuais > total) {
				pontosAtualizados = (int) pontosAtuais - (int) total;
				
				tds.atualizarPontos(pontosAtualizados, usuarioId);
				Transacao transacao = new Transacao(dataHora,total,usuario);
				
				for(Produto p : produtos) {
					transacao.getProdutos().add(p);
					p.getTransacao().add(transacao);
				}
				//System.out.println(transacao.toString());
				
				transacoes.save(transacao);
				return ResponseEntity.ok(true);
			}
			else {
				return ResponseEntity.ok(false);
			}
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
}
