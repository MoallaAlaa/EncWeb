package tn.biat.encweb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private Long numeroCompte;

	@NotBlank
	private String nom;

	@NotBlank
	private String prenom;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "agence_id", referencedColumnName = "id")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Agence agence;
}
