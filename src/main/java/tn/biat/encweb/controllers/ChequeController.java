package tn.biat.encweb.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.biat.encweb.model.Bordereaux;
import tn.biat.encweb.model.ChequeTraiterEncaissement;
import tn.biat.encweb.payloads.requests.AddBordereauRequest;
import tn.biat.encweb.payloads.requests.AddChequeRequestt;
import tn.biat.encweb.service.ChequeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cheque")
public class ChequeController {

	@Autowired
	ChequeService chequeServ;

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

	@GetMapping("/AfficherListeChequeRejeter")
	public ResponseEntity<?> AfficherListeChequeRejeter() {

		return ResponseEntity.ok(chequeServ.ListeChequesRejeter());

	}

	@GetMapping("/ChequesRejetRecuParAgence")
	public ResponseEntity<?> ChequesRejetRecuParAgence(
			@RequestParam(value = "chequeId", required = false) Long chequeId) throws ParseException {
		chequeServ.ConfirmerChequesRejetRecu(chequeId);

		return ResponseEntity.ok(" succes !");
	}

	@GetMapping("/RejeterCheque")
	public ResponseEntity<?> RejeterCheque(@RequestParam(value = "chequeId", required = false) Long chequeId,
			@RequestParam(value = "motifRejet", required = false) String motifRejet) throws ParseException {
		chequeServ.RejetEncaissement(chequeId, motifRejet);
		return ResponseEntity.ok(" succes !");

	}

	@GetMapping("/AfficherListeChequeTraiter")
	public ResponseEntity<?> AfficherListeChequeTraiter() {

		return ResponseEntity.ok(chequeServ.ListeChequesTraiter());

	}

	@GetMapping("/AddChequeTraiter")
	public ResponseEntity<?> AddChequeTraiter(@RequestParam(value = "chequeId", required = false) Long chequeId,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "dateReceptionBanque", required = false) Date dateReceptionBanque,
			@RequestParam(value = "dateSortie", required = false) Date dateSortie,
			@RequestParam(value = "banqueId", required = false) Long banqueId,
			@RequestParam(value = "dateOuverture", required = false) Date dateOuverture,
			@RequestParam(value = "clientId", required = false) Long clientId

	) throws ParseException {

		chequeServ.CreateChequeTraiter(chequeId, status, dateReceptionBanque, dateSortie, banqueId, dateOuverture,
				clientId);
		return ResponseEntity.ok(" succes !");

	}

	@GetMapping("/RechercheTest")
	public ResponseEntity<?> RechercheTest(@RequestParam(value = "numBordereaux") Optional<Long> numBordereaux,
			@RequestParam(value = "numCheques") Optional<Long> numCheques,
			@RequestParam(value = "numCompte") Optional<Long> numCompte,
			@RequestParam(value = "devise") Optional<String> devise,
			@RequestParam(value = "montant") Optional<String> montant,
			@RequestParam(value = "dateBordereaux") Optional<String> dateBordereaux) throws ParseException {

		List<ChequeTraiterEncaissement> newListeC = chequeServ.RechercheSansMontantDate(numBordereaux.orElse(null),
				numCheques.orElse(null), numCompte.orElse(null), devise.orElse(null));
		List<ChequeTraiterEncaissement> ListeC = newListeC;

		String m = montant.orElse(null);
		String da = dateBordereaux.orElse(null);

		if ((!m.isEmpty()) && (da.isEmpty())) {

			float montants = Float.parseFloat(m);
			ListeC = chequeServ.RechercheAvecMontant(newListeC, montants);

		}

		if ((m.isEmpty()) && (!da.isEmpty())) {

			ListeC = chequeServ.RechercheAvecDate(newListeC, da);

		}

		if ((!m.isEmpty()) && (!da.isEmpty())) {
			float montants = Float.parseFloat(m);
			ListeC = chequeServ.RechercheAvecDate(chequeServ.RechercheAvecMontant(newListeC, montants), da);

		}

		return ResponseEntity.ok(ListeC);
	}

	@GetMapping("/Recherche")
	public ResponseEntity<?> Recherche(@RequestParam(value = "numBordereaux") Optional<Long> numBordereaux,
			@RequestParam(value = "numCheques") Optional<Long> numCheques,
			@RequestParam(value = "numCompte") Optional<Long> numCompte,
			@RequestParam(value = "devise") Optional<String> devise,
			@RequestParam(value = "montant") Optional<String> montant,
			@RequestParam(value = "dateBordereaux") Optional<String> dateBordereaux) throws ParseException {

		return ResponseEntity
				.ok(chequeServ.Recherche(numBordereaux, numCheques, numCompte, devise, montant, dateBordereaux));
	}

}
