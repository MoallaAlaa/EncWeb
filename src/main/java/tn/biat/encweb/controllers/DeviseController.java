package tn.biat.encweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.biat.encweb.service.DeviseService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/devise")
public class DeviseController {

	@Autowired
	DeviseService deviseService;

	@GetMapping("/ListeDevise")
	public ResponseEntity<?> ListeChequeRecu() {

		return ResponseEntity.ok(deviseService.ListeDevise());
	}

}
