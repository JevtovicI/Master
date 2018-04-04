package jwd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.model.Faul;


@Repository
public interface FaulRepository extends JpaRepository<Faul, Long>{
	
}
