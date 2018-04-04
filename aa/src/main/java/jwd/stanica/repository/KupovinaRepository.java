package jwd.stanica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.stanica.model.Kupovina;


@Repository
public interface KupovinaRepository extends JpaRepository<Kupovina, Long>{
	
}
