package com.greencode.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greencode.demo.model.Endereco;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	@Query("select e from Endereco e where e.cidade = :cidade")
	public List<Endereco> buscarPorCidade(@Param("cidade") String cidade);
	
	@Transactional
	@Modifying
	@Query("update Endereco set numero = :numero where idLocal = :local")
	public void atualizarNumero (@Param("numero") Integer numero,@Param("local") Long local);
	
}
