package jwd.pivo.service;

import java.util.List;

import jwd.pivo.model.Pivara;



public interface PivaraService {
	List<Pivara> findAll();
	Pivara findOne(Long id);
	void save(Pivara pivara);
	void remove(Long id);

}
