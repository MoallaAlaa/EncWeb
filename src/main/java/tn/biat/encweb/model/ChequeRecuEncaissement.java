package tn.biat.encweb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.biat.encweb.configurations.files.FileDB;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Table(name = "cheque_recu_encaissement")
public class ChequeRecuEncaissement extends Cheque {

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateReceptionEncaissement;

	public ChequeRecuEncaissement(Long id, @NotNull Long numCheque, @NotNull float montant, FileDB imgCheque,
			Devise devise, Bordereaux bordereaux, StatutEncaisssement statutEncaisssement,
			Date dateReceptionEncaissement) {
		super(id, numCheque, montant, imgCheque, devise, bordereaux, statutEncaisssement);
		// TODO Auto-generated constructor stub
		this.dateReceptionEncaissement = dateReceptionEncaissement;
	}

}
