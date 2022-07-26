package tn.biat.encweb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cheques")
@Audited
public class Cheque extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long numCheque;

	@NotNull
	private float montant;

	@OneToOne(cascade = CascadeType.ALL)
	private FileDB imgCheque;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "devise_id", referencedColumnName = "id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Devise devise;

	@ManyToOne
	@JoinColumn(name = "bordereaux_id", nullable = false)
	private Bordereaux bordereaux;

	@Enumerated(EnumType.STRING)
	private StatutEncaisssement statutEncaisssement;

	public Cheque(@NotNull Long numCheque, @NotNull float montant, Devise devise) {
		super();
		this.numCheque = numCheque;
		this.montant = montant;
		this.devise = devise;
	}

}
