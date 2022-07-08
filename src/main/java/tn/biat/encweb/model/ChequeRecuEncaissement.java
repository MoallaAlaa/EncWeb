package tn.biat.encweb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "cheque_recu_encaissement")
public class ChequeRecuEncaissement extends Cheque {

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateReceptionEncaissement;
}
