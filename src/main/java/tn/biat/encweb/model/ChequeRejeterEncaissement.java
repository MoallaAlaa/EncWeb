package tn.biat.encweb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Table(name = "cheque_rejet_encaissement")
public class ChequeRejeterEncaissement extends ChequeRecuEncaissement {

	@NotBlank
	private String motifRejet;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateRejetEncaissement;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateReceptionAgence;

}
