package jwd.stanica.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.stanica.model.Linija;


public interface LinijaService {
	Page<Linija> findAll(int pageNum);
	Linija findOne(Long id);
	void save(Linija linija);
	void remove(Long id);
	Page<Linija> findByPrevoznikId(int pageNum, Long prevoznikId);
	Page<Linija> pretraga(
			@Param("destinacija") String destinacija, 
			@Param("prevoznikId") Long prevoznikId, 
			@Param("maxC") Integer maxC,
			int pageRequest);
}
