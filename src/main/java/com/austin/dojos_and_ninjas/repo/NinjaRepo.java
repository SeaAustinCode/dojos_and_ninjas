package com.austin.dojos_and_ninjas.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.austin.dojos_and_ninjas.model.Ninja;

@Repository
public interface NinjaRepo extends CrudRepository<Ninja, Long> {
	List<Ninja> findAll(); //this is the same as DojoRepo. Does this need to be switched to findAllNinja ? 
}
