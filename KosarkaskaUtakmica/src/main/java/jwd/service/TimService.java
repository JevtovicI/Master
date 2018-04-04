package jwd.service;

import java.util.List;

import jwd.model.Tim;



public interface TimService {
	List<Tim> findAll();
	Tim findOne(Long id);
	void save(Tim tim);
	void remove(Long id);

}
