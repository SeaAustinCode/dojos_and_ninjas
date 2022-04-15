package com.austin.dojos_and_ninjas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.austin.dojos_and_ninjas.model.Dojo;
import com.austin.dojos_and_ninjas.model.Ninja;
import com.austin.dojos_and_ninjas.repo.DojoRepo;
import com.austin.dojos_and_ninjas.repo.NinjaRepo;

@Service
public class DojosAndNinjasService {  /// COULD TURN THIS INTO 2 sep service files

	// CRUD
	@Autowired // a simpler and easier way to do dependency injection
	private DojoRepo dojoRepo;
	
	@Autowired // a simpler and easier way to do dependency injection
	private NinjaRepo ninjaRepo;

	// READ ALL Dojos //
	public List<Dojo> allDojos() {
		return dojoRepo.findAll(); 
	}
	
	// READ ALL Ninjas //
	public List<Ninja> allNinjas() {
		return ninjaRepo.findAll();
	}

	// CREATE Dojo
	public Dojo createDojo(Dojo d) {

		return dojoRepo.save(d);
	}
	
	// CREATE Ninjas
	public Ninja createNinja(Ninja n) {

		return ninjaRepo.save(n);
	}

	// READ ONE Dojo
	public Dojo findDojo(Long id) {
		Optional<Dojo> optionalDojo = dojoRepo.findById(id);
		if (optionalDojo.isPresent()) {
			return optionalDojo.get();
		} else {
			return null;
		}
	}
	
	// READ ONE Ninja
	public Ninja findNinja(Long id) {
		Optional<Ninja> optionalNinja = ninjaRepo.findById(id);
		if (optionalNinja.isPresent()) {
			return optionalNinja.get();
		} else {
			return null;
		}
	}

	// UPDATE Dojo
	public Dojo updateDojo(Dojo d) {
		return dojoRepo.save(d);
	}
	
	// UPDATE Ninja
		public Ninja updateNinja(Ninja n) {
			return ninjaRepo.save(n);
		}

	// DELETE Dojo
	public void deleteDojo(Long id) {
		dojoRepo.deleteById(id);
	}
		
	// DELETE Ninja
	public void deleteNinja(Long id) {
		ninjaRepo.deleteById(id);
	
}
	}
