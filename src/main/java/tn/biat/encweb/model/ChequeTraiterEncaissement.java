package tn.biat.encweb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "cheque_traitee_encaissement")
public class ChequeTraiterEncaissement extends Cheque {

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	private Banque Banque;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateReceptionBanque;

	@NotBlank
	private String Statut;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateSortie;

}
