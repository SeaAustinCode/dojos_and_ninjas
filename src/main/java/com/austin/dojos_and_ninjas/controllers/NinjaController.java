package com.austin.dojos_and_ninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.austin.dojos_and_ninjas.model.Dojo;
import com.austin.dojos_and_ninjas.model.Ninja;
import com.austin.dojos_and_ninjas.service.DojosAndNinjasService;

@Controller
public class NinjaController {

	@Autowired
	private DojosAndNinjasService dojosAndNinjasService;

	@RequestMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> alldojos = dojosAndNinjasService.allDojos();
		model.addAttribute("alldojos", alldojos); //this fixed the error Neither BindingResult nor plain target object for bean name 'dojo' available as request attribute
		return "newninja.jsp";
	}
	
	@PostMapping("/ninjas/new")
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja,
			BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			List<Dojo> alldojos = dojosAndNinjasService.allDojos();
			model.addAttribute("alldojos", alldojos);
			return "newninja.jsp";
		} else {
			dojosAndNinjasService.createNinja(ninja);
			return "redirect:/ninjas/new";
		}
	}
}
