package jwd.apoteka.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.apoteka.model.Lek;

@Repository
public interface LekRepository extends JpaRepository<Lek, Long> {

	Page<Lek> findByApotekaId(Long apotekaId, Pageable pageRequest);
	Lek findByIdAndApotekaId(Long id, Long apotekaId);
	// Ovo dole ne radi
//	@Query("SELECT l FROM Lek l WHERE "
//			+ "(:apotekaId IS NULL OR l.apoteka.id = :apotekaId) AND "
//			+ "(:naziv IS NULL OR l.naziv like :naziv OR l.generickiNaziv like :naziv) "
////			+ " AND "
////			+ "(:naziv IS NULL OR l.generickiNaziv like :naziv)"
//			)
//	Page<Lek> pretraga(
//			@Param("apotekaId") Long apotekaId,
//			@Param("naziv") String naziv,
//			Pageable pageRequest);
//	
//	Page<Lek> findByApotekaIdOrNazivLikeOrGenerickiNazivLike(
//			Long apotekaId, 
//			String naziv, 
//			String generickiNaziv, 
//			Pageable pageRequest);
	
	Page<Lek> findByApotekaIdOrNazivOrGenerickiNaziv(
			Long apotekaId, 
			String naziv, 
			String generickiNaziv, 
			Pageable pageRequest);
	
//	Page<Stand> findBySajamId(Long sajamId, Pageable pageRequest);
//
//	@Query("SELECT s FROM Stand s WHERE "
//			+ "(:zakupac IS NULL or s.zakupac like :zakupac ) AND "
//			+ "(:minP IS NULL OR s.povrsina >= :minP  ) AND "
//			+ "(:maxP IS NULL OR s.povrsina <= :maxP)"
//			)
//	Page<Stand> pretraga(
//			@Param("zakupac") String zakupac, 
//			@Param("minP") Integer min, 
//			@Param("maxP") Integer max,
//			Pageable pageRequest);
}
