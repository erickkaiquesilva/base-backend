package com.greencode.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencode.mongo.dao.RecicladosRepository;
import com.greencode.mongo.model.Reciclados;

@CrossOrigin
@RestController
@RequestMapping("/reciclado")
public class RecicladosController {
	
	@Autowired
	private RecicladosRepository recicladoRepository;

	@PostMapping("criar")
	public ResponseEntity<String> cadastroMaquina(@RequestBody Reciclados rec) {
		recicladoRepository.save(rec);
		return ResponseEntity.ok("BOTO Reciclavel!!");
	}

	@GetMapping("buscar")
	public ResponseEntity<List<Reciclados>> obterPorStatus() {
		List<Reciclados> reciclaveis = recicladoRepository.findAll();
		return reciclaveis.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(reciclaveis);
	}
	
}
