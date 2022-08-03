package tn.biat.encweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.biat.encweb.model.Devise;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {

	public Devise findByLabel(String label);

}
