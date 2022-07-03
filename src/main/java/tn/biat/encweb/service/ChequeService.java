package tn.biat.encweb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.biat.encweb.configurations.files.FileDB;
import tn.biat.encweb.configurations.files.FileDBRepository;
import tn.biat.encweb.dao.ChequeRepository;
import tn.biat.encweb.model.Cheque;
import tn.biat.encweb.payloads.responses.MessageResponse;

@Service
public class ChequeService {
	
	@Autowired
	ChequeRepository chequeRepo;
	
	@Autowired
	FileDBRepository fileDBRepository;
	
	
public ResponseEntity<Object> addCheque(MultipartFile file, Cheque cheque) throws IOException {
	FileDB FileDB = new FileDB(cheque.getNumBorderaux().toString(), file.getContentType(), file.getBytes());
	List<Cheque> cheques = chequeRepo.findAll();
	int aux = 0;
	
	for (Cheque c : cheques) {
		if (c.getNumBorderaux() == cheque.getNumBorderaux()) {
			aux++;
		}
	}
	
	if (aux < 6) {
		if (file != null) {
			fileDBRepository.save(FileDB);
			cheque.setImgCheque(FileDB);

		}
		chequeRepo.save(cheque);
		return ResponseEntity.ok("Cheque ajouter avec succes !");
		
	} 
	
	else 
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Borderaux Full !"));
	
	
	
	
	
}
}