package tn.biat.encweb.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.biat.encweb.model.Bordereaux;
import tn.biat.encweb.model.Cheque;
import tn.biat.encweb.service.ChequeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cheque")
public class ChequeController {

	@Autowired
	ChequeService chequeServ;

	@PostMapping("/addCheque")
	public ResponseEntity<Object> addCheque(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "cheque", required = false) String cheque,
			@RequestParam(value = "bordereaux", required = false) String bordereaux) throws IOException {

		Cheque c = new ObjectMapper().readValue(cheque, Cheque.class);
		Bordereaux b = new ObjectMapper().readValue(bordereaux, Bordereaux.class);
		return chequeServ.addCheque(file, c, b);

	}

}
