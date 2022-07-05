package tn.biat.encweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.biat.encweb.model.Bordereaux;

@Repository
public interface BordereauxRepository extends JpaRepository<Bordereaux, Long> {

	boolean existsByNumBordereaux(Long numBordereaux);

	public Bordereaux findByNumBordereaux(Long numBordereaux);

}
