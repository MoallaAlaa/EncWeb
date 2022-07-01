package tn.biat.encweb.dao.userDao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.biat.encweb.model.user.ERole;
import tn.biat.encweb.model.user.Role;

@Repository

public interface RoleRepository extends JpaRepository <Role, Long> {
	Optional<Role> findByName(ERole name);
}