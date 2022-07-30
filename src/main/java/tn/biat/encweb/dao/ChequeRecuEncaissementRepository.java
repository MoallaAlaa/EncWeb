package tn.biat.encweb.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.biat.encweb.model.ChequeRecuEncaissement;

@Repository
public interface ChequeRecuEncaissementRepository extends JpaRepository<ChequeRecuEncaissement, Long> {

	@Modifying
	@Query(value = "insert into cheque_recu_encaissement (id,DATE_RECEPTION_ENCAISSEMENT) values (:id, :dateReceptionEncaissement)", nativeQuery = true)
	void insertChild(@Param("id") Long id, @Param("dateReceptionEncaissement") Date dateReceptionEncaissement);

}
