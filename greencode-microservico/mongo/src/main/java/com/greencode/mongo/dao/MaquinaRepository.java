package com.greencode.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.greencode.mongo.model.Maquina;



@Repository
public interface MaquinaRepository extends MongoRepository<Maquina, String>{
	
	@Query("{'status': ?0}")
	public List<Maquina> acharPorStatus(String status);
}
