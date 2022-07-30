package tn.biat.encweb.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tn.biat.encweb.configurations.files.FileDB;
import tn.biat.encweb.configurations.files.FileDBRepository;
import tn.biat.encweb.dao.BordereauxRepository;
import tn.biat.encweb.dao.ChequeRecuEncaissementRepository;
import tn.biat.encweb.dao.ChequeRejeterEncaissementRepository;
import tn.biat.encweb.dao.ChequeRepository;
import tn.biat.encweb.dao.ChequeTraiterEncaissementRepository;
import tn.biat.encweb.model.Bordereaux;
import tn.biat.encweb.model.Cheque;
import tn.biat.encweb.model.ChequeRecuEncaissement;
import tn.biat.encweb.model.ChequeRejeterEncaissement;
import tn.biat.encweb.model.ChequeTraiterEncaissement;
import tn.biat.encweb.model.StatutEncaisssement;
import tn.biat.encweb.payloads.requests.AddChequeRequestt;
import tn.biat.encweb.payloads.responses.FinJourneeTab;

@Service
public class ChequeService {

	@Autowired
	ChequeRepository chequeRepo;

	@Autowired
	ChequeRecuEncaissementRepository chequeRecuRepo;

	@Autowired
	ChequeRejeterEncaissementRepository chequeRejetRepo;

	@Autowired
	FileDBRepository fileDBRepository;

	@Autowired
	BordereauxRepository bordereauxRepo;

	@Autowired
	ChequeTraiterEncaissementRepository chequeTraiterRepo;

	@Transactional
	public ResponseEntity<Object> addCheque2(Bordereaux b, List<AddChequeRequestt> cs) {

		bordereauxRepo.save(b);

		for (AddChequeRequestt a : cs) {

			Cheque c = new Cheque(a.getNumCheque(), a.getMontant(), a.getDevise());
			c.setBordereaux(b);
			c.setStatutEncaisssement(StatutEncaisssement.Saisie);
			String img = a.getPhotos();
			String base64Image = img.split(",")[1];
			String aux = img.split(",")[0];
			String aux2 = aux.split(";")[0];
			String base64ImageType = aux2.split(":")[1];

			FileDB FileDB = new FileDB(c.getBordereaux().getNumBordereaux().toString(), base64ImageType,
					Base64.getDecoder().decode(base64Image));

			if (FileDB != null) {
				fileDBRepository.save(FileDB);
				c.setImgCheque(FileDB);

			}

			chequeRepo.save(c);

		}

		return null;
	}

	public List<Bordereaux> listeBordereauxsAenvoyee() {

		List<Bordereaux> bordereaux = bordereauxRepo.findAll();
		List<Bordereaux> newBordereaux = new ArrayList<Bordereaux>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = dateFormat.format(date);

		for (Bordereaux b : bordereaux) {

			if (now.equals(dateFormat.format(b.getDateBordereaux()))) {

				newBordereaux.add(b);
			}

		}

		return newBordereaux;
	}

	public List<FinJourneeTab> AfficherListeBordereauxFinJourne(List<Bordereaux> Bx) {
		FinJourneeTab f = new FinJourneeTab();
		List<FinJourneeTab> listFinJournee = new ArrayList<FinJourneeTab>();

		for (Bordereaux b : Bx) {
			f.setNumBordereaux(b.getNumBordereaux());
			f.setNbrCheques(b.getCheques().size());
			listFinJournee.add(f);
		}

		return listFinJournee;

	}

	@Transactional
	public void chequesEnvoyerAEncaissement(List<Bordereaux> Bx) {
		for (Bordereaux b : Bx) {
			Set<Cheque> Cs = b.getCheques();
			for (Cheque c : Cs) {
				c.setStatutEncaisssement(StatutEncaisssement.En_Route);
				chequeRepo.save(c);
			}
			bordereauxRepo.save(b);
		}
	}

	public List<Cheque> ListeChequesEnRouteEncaissement() {
		List<Cheque> cs = chequeRepo.findAll();
		List<Cheque> newCs = new ArrayList<Cheque>();

		for (Cheque c : cs) {
			if ((c.getStatutEncaisssement() == StatutEncaisssement.En_Route)
					|| (c.getStatutEncaisssement() == StatutEncaisssement.Saisie)) {
				newCs.add(c);

			}

		}

		return newCs;
	}

	@Transactional
	public void ConfirmerChequesRecu(Long chequeId) throws ParseException {

		Cheque c = chequeRepo.findById(chequeId).orElse(null);
		c.setStatutEncaisssement(StatutEncaisssement.Arrivee);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = dateFormat.format(date);
		Date dateNow = dateFormat.parse(now);

		chequeRecuRepo.insertChild(c.getId(), dateNow);
	}

	public List<ChequeRecuEncaissement> ListeChequesRecu() {

		return chequeRecuRepo.findAll();
	}

	public List<Cheque> ListeCheques() {

		return chequeRepo.findAll();
	}

	@Transactional
	public void RejetEncaissement(Long chequeId, String motifRejet) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = dateFormat.format(date);
		Date dateNow = dateFormat.parse(now);
		chequeRejetRepo.newChequeRejetes(chequeId, motifRejet, dateNow);

	}

	public List<ChequeRejeterEncaissement> ListeChequesRejeter() {

		return chequeRejetRepo.findAll();
	}

	@Transactional
	public void ConfirmerChequesRejetRecu(Long chequeId) throws ParseException {

		ChequeRejeterEncaissement c = chequeRejetRepo.findById(chequeId).orElse(null);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = dateFormat.format(date);
		Date dateNow = dateFormat.parse(now);
		c.setDateReceptionAgence(dateNow);
		chequeRejetRepo.save(c);
	}

	public List<ChequeTraiterEncaissement> ListeChequesTraiter() {

		return chequeTraiterRepo.findAll();
	}

	@Transactional
	public void CreateChequeTraiter(Long id, String status, Date dateReceptionBanque, Date dateSortie, Long banqueId,
			Date dateOuverture, Long clientId

	) {

		chequeTraiterRepo.newChequeTraitee(id, status, dateReceptionBanque, dateSortie, banqueId, dateOuverture,
				clientId);

	}
}
