package com.austin.dojos_and_ninjas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.austin.dojos_and_ninjas.model.Dojo;
import com.austin.dojos_and_ninjas.service.DojosAndNinjasService;

@Controller
public class DojoController {

	@Autowired
	private DojosAndNinjasService dojosAndNinjasService;
	
	@RequestMapping(value={"/dojos/new","", "/"})
	public String showCreateDojo(@ModelAttribute("dojo") Dojo dojo, Model model) {
		
		return "newdojo.jsp";
	}
	
	@PostMapping(value={"/dojos/new"}) // do not put multiple routes here only on requestmapping
	public String createDojo(@ModelAttribute("dojo") Dojo dojo,
			BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "newdojo.jsp"; 
		} else {
			
		} dojosAndNinjasService.createDojo(dojo);
		return "redirect:/dojos/new"; 
	}
	
	@GetMapping("/dojos/{id}")
	public String showOneDojo(@PathVariable("id")Long id, Model model) {
		Dojo dojo = dojosAndNinjasService.findDojo(id);
		model.addAttribute("dojo", dojo); //this adds the dojo value variable into the model dojo
		return "dojo.jsp";
	}
}
