package jwd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.model.Igrac;


@Repository
public interface IgracRepository extends JpaRepository<Igrac, Long>  {
	Page<Igrac> findByTimId(Long timId, Pageable pageRequest);
	
	@Query(
			"SELECT p FROM Igrac p WHERE"
			+ "(:imePrezime IS NULL OR p.imePrezime LIKE :imePrezime) AND"
			+ "(:broj IS NULL OR p.broj = :broj) AND"
			+ "(:timId IS NULL OR p.tim.id = :timId)"
		)
		Page<Igrac> pretraga(
				@Param("imePrezime") String imePrezime,
				@Param("broj") Integer broj,
				@Param("timId") Long timId,
				Pageable pageable);
	}
