package tn.biat.encweb.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.biat.encweb.model.ChequeRejeterEncaissement;

public interface ChequeRejeterEncaissementRepository extends JpaRepository<ChequeRejeterEncaissement, Long> {

	@Modifying
	@Query(value = "insert into cheque_rejet_encaissement (id,MOTIF_REJET,DATE_REJET_ENCAISSEMENT) values (:id,:motifRejet,:dateRejetEncaissement)", nativeQuery = true)
	void newChequeRejetes(@Param("id") Long id, @Param("motifRejet") String motifRejet,
			@Param("dateRejetEncaissement") Date dateRejetEncaissement);

}
