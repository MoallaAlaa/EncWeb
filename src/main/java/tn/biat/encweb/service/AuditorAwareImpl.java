package tn.biat.encweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import tn.biat.encweb.dao.userDao.UserRepository;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Autowired
	UserRepository userRepository;

	@Override
	public Optional<String> getCurrentAuditor() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		return Optional.of(username);
	}

}
