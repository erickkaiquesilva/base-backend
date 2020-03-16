package com.greencode.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greencode.demo.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
		
	//@Query("select e from Empresa e where e.cnpj := cnpj")
	public Empresa findByCnpj(Integer cnpj); 
	
}
