package jwd.apoteka.service;

import java.util.List;

import jwd.apoteka.model.Apoteka;

public interface ApotekaService {

	List<Apoteka> findAll();
	Apoteka findOne(Long id);
	Apoteka save(Apoteka apoteka);
	void delete(Long id);
}
