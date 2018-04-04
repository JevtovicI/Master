package jwd.apoteka.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.apoteka.model.Stand;

public interface StandService {
	Page<Stand> findAll(int pageNum);
	Stand findOne(Long id);
	void save(Stand stand);
	void remove(Long id);
	Page<Stand> findBySajamId(Long sajamId, int pageNum);
	Page<Stand> pretraga(
			@Param("zakupac") String zakupac, 
			@Param("minP") Integer min, 
			@Param("maxP") Integer max,
			int page);
}
