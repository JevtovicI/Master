package jwd.knjizara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.knjizara.model.Glasanje;


@Repository
public interface GlasanjeRepository extends JpaRepository<Glasanje, Long>{
	
}
