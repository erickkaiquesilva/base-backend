package com.greencode.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.greencode.mongo.model.Reciclavel;

public interface ReciclavelRepository extends MongoRepository<Reciclavel, String>{
	
	@Query("{'status': ?0}")
	public List<Reciclavel> acharPorTipo(String tipo);
}
