package tn.biat.encweb.payloads.requests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AddBordereauRequest {

	@NotNull
	private Long numBordereaux;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateBordereaux;

	private List<AddChequeRequestt> cheques = new ArrayList<AddChequeRequestt>();

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

	public List<AddChequeRequestt> getCheques() {
		return cheques;
	}

	public void setCheques(List<AddChequeRequestt> cheques) {
		this.cheques = cheques;
	}

}
