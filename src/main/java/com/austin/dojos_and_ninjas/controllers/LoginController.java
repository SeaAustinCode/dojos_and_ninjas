package com.austin.dojos_and_ninjas.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.austin.dojos_and_ninjas.model.LoginUser;
import com.austin.dojos_and_ninjas.model.User;
import com.austin.dojos_and_ninjas.service.UserService;

//.. don't forget to include all your imports! ..

@Controller
public class LoginController {

	// Add once service is implemented:
	@Autowired
	private UserService userServ;

	// RENDER LOGIN and REG PAGE // This Login and Registration does not force you to go to the dashboard if you are in session - work to change this for project.
	@GetMapping("/") // this will have the login and registration form 
	public String index(Model model) {

		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp"; // need to create this maybe this could be my login and registration page
	}

	// REGISTER NEW USER //
	@PostMapping("/register/method") // call these types of routes ____/method ===> this means that we don't actually see them they are used for processing 
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {
//    execute the Service to Register FIRST! 
		userServ.register(newUser, result);

		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp"; // if you get the error of it taking you to the wrong page return the jsp vs a route 
		} else {
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/home";
		}
	}

	// LOGIN USER //

	@PostMapping("/login/method")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult res, Model model,
			HttpSession sesh) {
		User user = userServ.login(newLogin, res);

		if (res.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {
			sesh.setAttribute("user_id", user.getId());
			return "redirect:/home";
		}
	}

	// LOGOUT USER //
	@GetMapping("/logout/method")
	public String logout(HttpSession sesh) {
		sesh.invalidate();
		return "redirect:/home";
	}

// HOME ROUTE //
	@RequestMapping("/home")
	public String home(HttpSession session, Model model) {
		// retrieve the user from session
		Long userId = (Long) session.getAttribute("user_id");
		// check if userId is null
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			model.addAttribute("thisUser", thisUser);
			return "home.jsp";
		}
	}

}
