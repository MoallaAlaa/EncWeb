package tn.biat.encweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.biat.encweb.model.Bordereaux;
import tn.biat.encweb.model.Cheque;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {

	long countByBordereaux(Bordereaux bordereaux);

}