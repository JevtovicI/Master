package jwd.apoteka.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.apoteka.model.Apoteka;
import jwd.apoteka.repository.ApotekaRepository;
import jwd.apoteka.service.ApotekaService;

@Service
@Transactional
public class JpaApotekaService implements ApotekaService {

	@Autowired
	private ApotekaRepository apotekaRepository;
	
	@Override
	public List<Apoteka> findAll() {
		return apotekaRepository.findAll();
	}

	@Override
	public Apoteka findOne(Long id) {
		return apotekaRepository.findOne(id);
	}

	@Override
	public Apoteka save(Apoteka apoteka) {
		return apotekaRepository.save(apoteka);
	}

	@Override
	public void delete(Long id) {
		apotekaRepository.delete(id);
	}

}
