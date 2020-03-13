package com.greencode.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greencode.demo.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	
	@Query(value = "select t.data_hora as datahora, t.valor_transacao as valor,prod.nome as nome, "
			+ "t.id as id from tb_transacao as t join produtos_transacao as p join tb_produto prod" + 
			" where usuario_id = :id and t.id = p.transacao_id and p.produtos_id = prod.id_produto order by t.id;", nativeQuery = true)
	public List<Object> buscarPorUsuario(@Param("id") Long id);
	
}
