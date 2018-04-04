package jwd.apoteka.service;

import java.util.List;

import jwd.apoteka.model.Proizvodjac;

public interface ProizvodjacService {

	List<Proizvodjac> findAll();
	Proizvodjac findOne(Long id);
	Proizvodjac save(Proizvodjac proizvodjac);
	void delete(Long id);
}
