package tn.biat.encweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.biat.encweb.model.Devise;

public interface DeviseRepository extends JpaRepository<Devise, Long> {

}
