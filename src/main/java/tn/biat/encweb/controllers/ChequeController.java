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
import tn.biat.encweb.payloads.requests.AddBordereauRequest;
import tn.biat.encweb.payloads.requests.AddChequeRequest;
import tn.biat.encweb.payloads.requests.AddChequeRequestt;
import tn.biat.encweb.service.ChequeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cheque")
public class ChequeController {

	@Autowired
	ChequeService chequeServ;

	@PostMapping("/addCheque")
	public ResponseEntity<Object> addCheque(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "cheque", required = true) String cheque) throws IOException {

		AddChequeRequest cb = new ObjectMapper().readValue(cheque, AddChequeRequest.class);

		Cheque c = new Cheque(cb.getNumCheque(), cb.getMontant(), cb.getDevise());
		Bordereaux b = new Bordereaux(cb.getNumBordereaux(), cb.getDateBordereaux());

		return chequeServ.addCheque(file, c, b);

	}

	@PostMapping("/addCheque2")
	public ResponseEntity<Object> addCheque2(@RequestParam(value = "bordereau", required = true) String bordereau)
			throws IOException {

		AddBordereauRequest cb = new ObjectMapper().readValue(bordereau, AddBordereauRequest.class);
		Bordereaux b = new Bordereaux(cb.getNumBordereaux(), cb.getDateBordereaux());
		List<AddChequeRequestt> cs = cb.getCheques();

		return chequeServ.addCheque2(b, cs);

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

	@GetMapping("/AfficherListeCheque")
	public ResponseEntity<?> AfficherListeCheque() {

		return ResponseEntity.ok(chequeServ.ListeCheques());
	}
}
