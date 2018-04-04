package jwd.knjizara.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.knjizara.model.Knjiga;


@Repository
public interface KnjigaRepository extends JpaRepository<Knjiga, Long>  {
	Page<Knjiga> findByIzdavacId(Long izdavacId, Pageable pageRequest);
	
	@Query("SELECT l FROM Knjiga l WHERE "
			+ "(:naziv IS NULL or l.naziv like :naziv ) AND "
			+ "(:pisac IS NULL OR l.pisac = :pisac  ) AND "
			+ "(:minG IS NULL OR l.brojGlasova >= :minC)"
			)
	Page<Knjiga> pretraga(
			@Param("naziv") String naziv, 
			@Param("pisac") String pisac, 
			@Param("minG") Integer minG,
			Pageable pageRequest);
}
