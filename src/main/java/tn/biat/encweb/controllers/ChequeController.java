package tn.biat.encweb.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/ChequesAenvoyer")
	public ResponseEntity<?> AfficherFinJournee() {
		List<Bordereaux> b = chequeServ.listeBordereauxsAenvoyee();

		return ResponseEntity.ok(chequeServ.AfficherListeBordereauxFinJourne(b));
	}

	@GetMapping("/finJournee")
	public ResponseEntity<?> FinJournee() {
		List<Bordereaux> b = chequeServ.listeBordereauxsAenvoyee();
		chequeServ.chequesEnvoyerAEncaissement(b);
		return ResponseEntity.ok(" succes !");
	}

	@GetMapping("/ChequesEnRoute")
	public ResponseEntity<?> AfficherChequesEnRoute() {

		return ResponseEntity.ok(chequeServ.ListeChequesEnRouteEncaissement());
	}

	@GetMapping("/ChequesRecuParEncaissement")
	public ResponseEntity<?> ChequesRecuParEncaissement(@RequestParam(value = "cheque", required = false) Long chequeId)
			throws ParseException {
		chequeServ.ConfirmerChequesRecu(chequeId);

		return ResponseEntity.ok(" succes !");
	}

	@GetMapping("/AfficherListeChequeRecu")
	public ResponseEntity<?> AfficherListeChequeRecu() {

		return ResponseEntity.ok(chequeServ.ListeChequesRecu());
	}
}
