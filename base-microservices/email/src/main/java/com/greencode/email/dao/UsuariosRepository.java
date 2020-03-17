package com.greencode.email.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greencode.email.model.Usuario;


@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where u.email = :email and u.senha = :senha")
	public Usuario logar(@Param("email") String email,@Param("senha") String senha);
	
	@Query("select u from Usuario u where u.email = :email")
	public Usuario buscarEmail(@Param("email") String email);
	
	@Query("select u.pontos from Usuario u where u.id = :id")
	public int buscarPontosPorId(Long id);
	
	@Transactional
	@Modifying
	@Query("update Usuario set pontos = :pontos where id = :id")
	public void atualizarPontos (@Param("pontos") int pontos,@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("update Usuario set senha = :senha where id = :id")
	public void atualizarSenha (@Param("senha") String senha,@Param("id") Long id);
	
}