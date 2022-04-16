package com.austin.dojos_and_ninjas.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.austin.dojos_and_ninjas.model.LoginUser;
import com.austin.dojos_and_ninjas.model.User;
import com.austin.dojos_and_ninjas.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User register(User newUser, BindingResult result) {
		// Reject if the email is already in use (in database)
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "login_errors", "Email is already in use"); // this takes in a string, key code
																					// that can be called other places,
																					// default message
		}
		// Reject if password != confirm password
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "registerErrors", "confirm password and password must exactly match.");
		}
		// return null if result has errors
		if (result.hasErrors()) {
			return null;
		} else { // if the user passes all the validations -- register the user and put them in
					// the database
			String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashed);
			// SAVE USER TO THE DATABASE
			return userRepo.save(newUser);
		}
	}

	public User login(LoginUser newLoginObject, BindingResult result) {
		// FIND THE USER IN THE DataBase
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "loginError", "email not found");
		} else {
			User user = potentialUser.get();
			// Reject if BCyrpt pass match does not work
			if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
				result.rejectValue("password", "loginError", "invalid password");
			}
			// return null if result has errors
			if (result.hasErrors()) {
				return null;
			} else {
				// otherwise, return the user object
				return user;
			}
		}
		return null;
	}

	// FIND ONE BY ID
	public User findOne(Long id) {
		userRepo.findById(id);
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			return potentialUser.get();
		} else {
			return null;
		}
	}

	// FIND ONE BY EMAIL
	public User findByEmail(String email) {
		userRepo.findByEmail(email);
		Optional<User> userByEmail = userRepo.findByEmail(email);
		if (userByEmail.isPresent()) {
			return userByEmail.get();
		} else {
			return null;
		}
	}
}
