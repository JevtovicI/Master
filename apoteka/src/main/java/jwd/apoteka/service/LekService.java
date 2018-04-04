package jwd.apoteka.service;

import org.springframework.data.domain.Page;

import jwd.apoteka.model.Lek;

public interface LekService {

	Page<Lek> findAll(int pageNum);
	Lek findOne(Long id);
	Lek save(Lek lek);
	void delete(Long id);
//	Page<Stand> findBySajamId(Long sajamId, int pageNum);
//	Page<Stand> pretraga(
//			@Param("zakupac") String zakupac, 
//			@Param("minP") Integer min, 
//			@Param("maxP") Integer max,
//			int page);
	Page<Lek> findByApotekaId(Long apotekaId, int page);
	Lek findByIdAndApotekaId(Long id, Long id_apoteke);
//	Page<Lek> pretraga(Long apotekaId, String naziv, int page);
//	Page<Lek> findByApotekaIdOrNazivLikeOrGenerickiNazivLike(
//			Long apotekaId, 
//			String naziv, 
//			String generickiNaziv, 
//			int page);
	
	Page<Lek> findByApotekaIdOrNazivOrGenerickiNaziv(
			Long apotekaId, 
			String naziv, 
			String generickiNaziv, 
			int page);
//	Page<Lek> findByNazivOrGenerickiNazivLike(String naziv,  int page);
}
