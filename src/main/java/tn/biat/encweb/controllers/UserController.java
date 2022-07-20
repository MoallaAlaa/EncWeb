package tn.biat.encweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.biat.encweb.dao.userDao.UserRepository;
import tn.biat.encweb.model.user.User;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/userCrud")

public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/agentById/{id}")

	public User employeeById(@PathVariable("id") Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
}
