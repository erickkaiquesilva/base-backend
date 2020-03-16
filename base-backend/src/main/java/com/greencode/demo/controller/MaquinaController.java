package com.greencode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greencode.demo.dao.MaquinaRepository;
import com.greencode.demo.model.Maquina;

@RestController
public class MaquinaController {
	
	private MaquinaRepository tdMaquinas;
	
	@Autowired
	public MaquinaController(MaquinaRepository maquinas) {
		tdMaquinas = maquinas;
	}
	
	@PostMapping("/maquina/criar")
	public ResponseEntity<Boolean> cadastrarMaquina(@RequestBody Maquina maquina){
		
		tdMaquinas.save(maquina);
		
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/maquina/deletar")
	public ResponseEntity<Boolean> deletarEndereco(@RequestBody Maquina maquina){
		
		tdMaquinas.delete(maquina);
		
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/maquina/buscarPorTipo/{tipo}")
	public ResponseEntity<List<Maquina>> buscarPorCidade(@PathVariable String tipo){
		
		List<Maquina> maquinasAchadas = tdMaquinas.findByTipoMaquina(tipo);
		
		return ResponseEntity.ok(maquinasAchadas);
	}
	
	@PostMapping("/maquina/atualizarStatus")
	public ResponseEntity<Boolean> atualizarStatus(@RequestBody Maquina maquina){
		tdMaquinas.atualizarStatus(maquina.getStatus(), maquina.getId());
		
		return ResponseEntity.ok(true);
	}
}
