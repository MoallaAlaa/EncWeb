package tn.biat.encweb.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.biat.encweb.model.ChequeTraiterEncaissement;

@Repository
public interface ChequeTraiterEncaissementRepository extends JpaRepository<ChequeTraiterEncaissement, Long> {

	@Modifying
	@Query(value = "insert into cheque_traitee_encaissement (id,STATUT,DATE_RECEPTION_BANQUE,DATE_SORTIE,BANQUE_ID,DATE_OUVERTURE,CLIENT_ID) values"
			+ " (:id,:status,:dateReceptionBanque,:dateSortie,:banqueId,:dateOuverture,:clientId)", nativeQuery = true)

	void newChequeTraitee(@Param("id") Long id, @Param("status") String status,
			@Param("dateReceptionBanque") Date dateReceptionBanque, @Param("dateSortie") Date dateSortie,
			@Param("banqueId") Long banqueId, @Param("dateOuverture") Date dateOuverture,
			@Param("clientId") Long clientId

	);

}
