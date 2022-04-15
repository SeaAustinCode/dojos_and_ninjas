package com.austin.dojos_and_ninjas.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.austin.dojos_and_ninjas.model.Dojo;

@Repository
public interface DojoRepo extends CrudRepository<Dojo, Long> {
	List<Dojo> findAll(); //this is the same as NinjaRepo. Does this need to be switched to findAllDojo ? 
	
}
