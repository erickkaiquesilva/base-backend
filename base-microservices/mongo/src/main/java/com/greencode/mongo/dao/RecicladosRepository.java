package com.greencode.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.greencode.mongo.model.Reciclados;

public interface RecicladosRepository  extends MongoRepository<Reciclados, String>{
	
}
