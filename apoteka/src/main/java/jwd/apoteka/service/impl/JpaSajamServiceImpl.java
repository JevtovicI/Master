package jwd.apoteka.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.apoteka.model.Sajam;
import jwd.apoteka.repository.SajamRepository;
import jwd.apoteka.service.SajamService;

@Service
@Transactional
public class JpaSajamServiceImpl implements SajamService {
	@Autowired
	private SajamRepository sajamRepository;

	@Override
	public List<Sajam> findAll() {
		return sajamRepository.findAll();
	}

	@Override
	public Sajam findOne(Long id) {
		return sajamRepository.findOne(id);
	}

	@Override
	public void save(Sajam sajam) {
		sajamRepository.save(sajam);
	}

	@Override
	public void remove(Long id) {
		sajamRepository.delete(id);
	}

	
	
}
