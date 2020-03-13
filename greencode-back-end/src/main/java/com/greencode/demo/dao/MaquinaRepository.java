package com.greencode.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greencode.demo.model.Maquina;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long>{
	
	//@Query("select m from Maquina m where m.tipoMaquina = :tipo")
	public List<Maquina> findByTipoMaquina(String tipo);
	
	@Transactional
	@Modifying
	@Query("update Maquina set status = :status where id = :id")
	public void atualizarStatus (@Param("status") String status,@Param("id") Long id);
	
}
