package jwd.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.model.Igrac;


public interface IgracService {
	Page<Igrac> findAll(int pageNum);
	Igrac findOne(Long id);
	void save(Igrac igrac);
	void remove(Long id);
	Page<Igrac> findByTimId(int pageNum, Long timId);
	Page<Igrac> pretraga(
			@Param("imePrezime") String imePrezime,
			@Param("broj") Integer broj,
			@Param("timId") Long timId,
			int pageRequest);
}
