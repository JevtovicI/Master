package jwd.stanica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.stanica.model.Linija;


@Repository
public interface LinijaRepository extends JpaRepository<Linija, Long>  {
	Page<Linija> findByPrevoznikId(Long prevoznikId, Pageable pageRequest);
	
	@Query("SELECT l FROM Linija l WHERE "
			+ "(:destinacija IS NULL or l.destinacija like :destinacija ) AND "
			+ "(:prevoznikId IS NULL OR l.prevoznik.id = :prevoznikId  ) AND "
			+ "(:maxC IS NULL OR l.cenaKarte <= :maxC)"
			)
	Page<Linija> pretraga(
			@Param("destinacija") String destinacija, 
			@Param("prevoznikId") Long prevoznikId, 
			@Param("maxC") Integer maxC,
			Pageable pageRequest);
}
