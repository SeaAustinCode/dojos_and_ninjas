package com.austin.dojos_and_ninjas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.austin.dojos_and_ninjas.model.Ninja;
import com.austin.dojos_and_ninjas.service.DojosAndNinjasService;
import com.austin.dojos_and_ninjas.service.UserService;

@Controller
public class NinjaController {

	@Autowired
	private DojosAndNinjasService dojosAndNinjasService;
	@Autowired
	private UserService userService;

	@RequestMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> alldojos = dojosAndNinjasService.allDojos();
		model.addAttribute("alldojos", alldojos); //this fixed the error Neither BindingResult nor plain target object for bean name 'dojo' available as request attribute
		return "newninja.jsp";
	}
	
	@PostMapping("/ninjas/new")
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja,
			BindingResult result, Model model, HttpSession sesh) {
		
		if (result.hasErrors()) {
			List<Dojo> alldojos = dojosAndNinjasService.allDojos();
			model.addAttribute("alldojos", alldojos);
			return "newninja.jsp";
		} else {
			dojosAndNinjasService.createNinja(ninja);
			return "redirect:/ninjas/new";
		}
	}
	
	// SHOW ALL THE NINJAS
	@GetMapping("/ninjas")
	public String index(Model model, HttpSession session) {
		
		Long userId = (Long) session.getAttribute("user_id"); //get the user_id to check if you are in session
		// check if userID is null
		if(userId == null) {
			return "redirect:/";
		} else {
			List<Ninja> allninjas = dojosAndNinjasService.allNinjas();
			model.addAttribute("allninjas", allninjas);
			return "/ninjas/index.jsp";
		}
	}
	
	// SHOW ONE RENDER - SHOW ONE NINJA AND ITS DETAILS 
	// example - localhost:8080/ninjas/3
	@GetMapping("/ninjas/{id}")
	public String showOneNinja(@PathVariable("id") Long id, Model model) {
		Ninja oneNinja = dojosAndNinjasService.findNinja(id);
		model.addAttribute("oneNinja", oneNinja);
		return "/ninjas/show.jsp";
	}
}
