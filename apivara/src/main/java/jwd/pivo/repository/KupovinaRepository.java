package jwd.pivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.pivo.model.Kupovina;


@Repository
public interface KupovinaRepository extends JpaRepository<Kupovina, Long>{
	
}
