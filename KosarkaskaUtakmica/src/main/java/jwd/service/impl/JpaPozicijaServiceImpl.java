package jwd.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.model.Pozicija;
import jwd.repository.PozicijaRepository;
import jwd.service.PozicijaService;


@Service
@Transactional
public class JpaPozicijaServiceImpl implements PozicijaService {
	@Autowired
	private PozicijaRepository pozicijaRepository;

	@Override
	public List<Pozicija> findAll() {
		return pozicijaRepository.findAll();
	}

	@Override
	public Pozicija findOne(Long id) {
		return pozicijaRepository.findOne(id);
	}

	@Override
	public void save(Pozicija pozicija) {
		pozicijaRepository.save(pozicija);
	}

	@Override
	public void remove(Long id) {
		pozicijaRepository.delete(id);
	}

	
	
}
