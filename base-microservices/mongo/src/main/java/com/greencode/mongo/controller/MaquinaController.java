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

import com.greencode.mongo.dao.MaquinaRepository;
import com.greencode.mongo.model.Maquina;

@CrossOrigin
@RestController
@RequestMapping("/maquina")
public class MaquinaController {
	
	@Autowired
	private MaquinaRepository maquinaRepository;

	@PostMapping("criar")
	public ResponseEntity<String> cadastroMaquina(@RequestBody Maquina maq) {
		maquinaRepository.save(maq);
		return ResponseEntity.ok("BOTO Reciclavel");
	}

	@GetMapping("buscar/{status}")
	public ResponseEntity<List<Maquina>> obterPorStatus(@PathVariable("status") String status) {
		List<Maquina> maquinaPorStatus = maquinaRepository.acharPorStatus(status);
		return maquinaPorStatus.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(maquinaPorStatus);
	}

}
