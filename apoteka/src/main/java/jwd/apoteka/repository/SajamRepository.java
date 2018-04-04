package jwd.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.apoteka.model.Sajam;

@Repository
public interface SajamRepository 
	extends JpaRepository<Sajam, Long> {

}
