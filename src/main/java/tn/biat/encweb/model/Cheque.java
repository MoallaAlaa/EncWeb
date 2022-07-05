package tn.biat.encweb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

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
@Table(name = "cheques", uniqueConstraints = { @UniqueConstraint(columnNames = "numCheque"), })
public class Cheque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long numCheque;

	@NotNull
	private float montant;

	@OneToOne(cascade = CascadeType.ALL)
	private FileDB imgCheque;

	@Enumerated(EnumType.STRING)
	private Devise devise;

	@ManyToOne
	@JoinColumn(name = "bordereaux_id", nullable = false)
	private Bordereaux bordereaux;

}
