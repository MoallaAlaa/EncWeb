package tn.biat.encweb.payloads.requests;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import tn.biat.encweb.model.Devise;

public class AddChequeRequest {

	@NotNull
	private Long numBordereaux;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateBordereaux;

	@NotNull
	private Long numCheque;

	@NotNull
	private float montant;

	@NotNull
	private Devise devise;

	public Long getNumBordereaux() {
		return numBordereaux;
	}

	public void setNumBordereaux(Long numBordereaux) {
		this.numBordereaux = numBordereaux;
	}

	public Date getDateBordereaux() {
		return dateBordereaux;
	}

	public void setDateBordereaux(Date dateBordereaux) {
		this.dateBordereaux = dateBordereaux;
	}

	public Long getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(Long numCheque) {
		this.numCheque = numCheque;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Devise getDevise() {
		return devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
	}

}
