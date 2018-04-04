package jwd.apoteka.service;

import java.util.List;

import jwd.apoteka.model.Sajam;


public interface SajamService {
	List<Sajam> findAll();
	Sajam findOne(Long id);
	void save(Sajam sajam);
	void remove(Long id);

}
