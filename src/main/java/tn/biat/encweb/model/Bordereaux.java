package tn.biat.encweb.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "bordereaux", uniqueConstraints = { @UniqueConstraint(columnNames = "numBordereaux"), })
@Audited
public class Bordereaux extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long numBordereaux;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateBordereaux;

	@JsonIgnore
	@OneToMany(mappedBy = "bordereaux")
	private Set<Cheque> cheques = new HashSet<>();

	public Bordereaux(@NotNull Long numBordereaux, Date dateBordereaux) {
		super();
		this.numBordereaux = numBordereaux;
		this.dateBordereaux = dateBordereaux;
	}

}
