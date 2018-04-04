package jwd.service;

import java.util.List;

import jwd.model.Pozicija;

public interface PozicijaService {
	List<Pozicija> findAll();

	Pozicija findOne(Long id);

	void save(Pozicija pozicija);

	void remove(Long id);

}
