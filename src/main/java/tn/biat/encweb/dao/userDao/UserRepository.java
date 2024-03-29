package tn.biat.encweb.dao.userDao;

import org.springframework.stereotype.Repository;
import tn.biat.encweb.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	
}
