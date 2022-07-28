package tn.biat.encweb.payloads.requests;

import javax.validation.constraints.NotNull;

import tn.biat.encweb.model.Devise;

public class AddChequeRequestt {

	@NotNull
	private Long numCheque;

	@NotNull
	private float montant;

	@NotNull
	private Devise devise;

	@NotNull
	private String photos;

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

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

}
