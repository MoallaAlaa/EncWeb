package tn.biat.encweb.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.biat.encweb.configurations.files.FileDB;
import tn.biat.encweb.configurations.files.FileDBRepository;
import tn.biat.encweb.dao.BordereauxRepository;
import tn.biat.encweb.dao.ChequeRepository;
import tn.biat.encweb.model.Bordereaux;
import tn.biat.encweb.model.Cheque;
import tn.biat.encweb.payloads.responses.MessageResponse;

@Service
public class ChequeService {

	@Autowired
	ChequeRepository chequeRepo;

	@Autowired
	FileDBRepository fileDBRepository;

	@Autowired
	BordereauxRepository bordereauxRepo;

	@Transactional
	public ResponseEntity<Object> addCheque(MultipartFile file, Cheque cheque, Bordereaux bordereaux)
			throws IOException {

		int nbrCheques = 0;

		FileDB FileDB = new FileDB(bordereaux.getNumBordereaux().toString(), file.getContentType(), file.getBytes());

		List<Cheque> cheques = chequeRepo.findAll();

		cheque.setBordereaux(bordereaux);

		for (Cheque c : cheques) {
			if (c.getBordereaux().getNumBordereaux() == cheque.getBordereaux().getNumBordereaux()) {
				nbrCheques++;
			}
		}

		if (nbrCheques < 6) {
			if (file != null) {
				fileDBRepository.save(FileDB);
				cheque.setImgCheque(FileDB);

			}

			bordereaux.getCheques().add(cheque);

			if (bordereauxRepo.existsByNumBordereaux(bordereaux.getNumBordereaux())) {

				bordereaux.setId(bordereauxRepo.findByNumBordereaux(bordereaux.getNumBordereaux()).getId());
				bordereauxRepo.save(bordereaux);

			} else {

				bordereauxRepo.save(bordereaux);

			}

			chequeRepo.save(cheque);
			return ResponseEntity.ok("Cheque ajouter avec succes !");

		}

		else
			return ResponseEntity.badRequest().body(new MessageResponse("Borderaux Full !"));

	}
}