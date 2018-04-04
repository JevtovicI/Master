package jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.model.Pozicija;



@Repository
public interface PozicijaRepository extends JpaRepository<Pozicija, Long> {
}
