package tn.biat.encweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.biat.encweb.dao.DeviseRepository;
import tn.biat.encweb.model.Devise;

@Service
public class DeviseService {

	@Autowired
	DeviseRepository deviseRepo;

	public List<Devise> ListeDevise() {
		return deviseRepo.findAll();
	}

}
