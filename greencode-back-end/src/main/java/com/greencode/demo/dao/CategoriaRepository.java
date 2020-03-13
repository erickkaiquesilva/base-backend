package com.greencode.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greencode.demo.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	//@Query("select c from Categoria c where c.nome = :nome")
	public List<Categoria> findByNome(String nome);
	
}
