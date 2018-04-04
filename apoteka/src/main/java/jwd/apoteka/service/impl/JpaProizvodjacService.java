package jwd.apoteka.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.apoteka.model.Proizvodjac;
import jwd.apoteka.repository.ProizvodjacRepository;
import jwd.apoteka.service.ProizvodjacService;

@Service
@Transactional
public class JpaProizvodjacService implements ProizvodjacService {

	@Autowired
	private ProizvodjacRepository proizvodjacRepository;
	
	@Override
	public List<Proizvodjac> findAll() {
		return proizvodjacRepository.findAll();
	}

	@Override
	public Proizvodjac findOne(Long id) {
		return proizvodjacRepository.findOne(id);
	}

	@Override
	public Proizvodjac save(Proizvodjac proizvodjac) {
		return proizvodjacRepository.save(proizvodjac);
	}

	@Override
	public void delete(Long id) {
		proizvodjacRepository.delete(id);
	}

}
