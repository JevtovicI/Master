package jwd.pivo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.pivo.model.Pivara;
import jwd.pivo.repository.PivaraRepository;
import jwd.pivo.service.PivaraService;

@Service
@Transactional
public class JpaPivaraServiceImpl implements PivaraService {
	@Autowired
	private PivaraRepository pivaraRepository;

	@Override
	public List<Pivara> findAll() {
		return pivaraRepository.findAll();
	}

	@Override
	public Pivara findOne(Long id) {
		return pivaraRepository.findOne(id);
	}

	@Override
	public void save(Pivara pivara) {
		pivaraRepository.save(pivara);
	}

	@Override
	public void remove(Long id) {
		pivaraRepository.delete(id);
	}

	
	
}
