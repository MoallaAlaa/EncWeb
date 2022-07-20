package tn.biat.encweb.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"), })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 120)
	private String nom;

	@NotBlank
	@Size(max = 120)
	private String prenom;

	@NotBlank
	@Size(max = 120)
	private String codeAgence;

	@NotBlank
	@Size(max = 120)
	private String nomAgence;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password) {
		super();
		this.username = username;
		this.password = password;

	}

	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password,
			@NotBlank @Size(max = 120) String nom, @NotBlank @Size(max = 120) String prenom,
			@NotBlank @Size(max = 120) String codeAgence, @NotBlank @Size(max = 120) String nomAgence) {
		super();
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.codeAgence = codeAgence;
		this.nomAgence = nomAgence;
	}

}
