package tn.biat.encweb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

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
@Table(name = "cheque_traitee_encaissement")
public class ChequeTraiterEncaissement extends ChequeRecuEncaissement {

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@JoinColumn(name = "banque_id", referencedColumnName = "id")
	private Banque Banque;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateReceptionBanque;

	@NotBlank
	private String Statut;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateSortie;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateOuverture;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

}
