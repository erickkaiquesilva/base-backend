package com.greencode.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencode.mongo.dao.ReciclavelRepository;
import com.greencode.mongo.model.Reciclavel;

@CrossOrigin
@RestController
@RequestMapping("/reciclavel")
public class ReciclavelController {
	
	@Autowired
	private ReciclavelRepository reciclavelRepository;

	@PostMapping("/criar")
	public ResponseEntity<String> cadastroMaquina(@RequestBody Reciclavel rec) {
		reciclavelRepository.save(rec);
		return ResponseEntity.ok("BOTO Reciclavel!!");
	}

	@GetMapping("/buscar/{status}")
	public ResponseEntity<List<Reciclavel>> obterPorStatus(@PathVariable("status") String tipo) {
		List<Reciclavel> reciclaveis = reciclavelRepository.acharPorTipo(tipo);
		return reciclaveis.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(reciclaveis);
	}
	
}
