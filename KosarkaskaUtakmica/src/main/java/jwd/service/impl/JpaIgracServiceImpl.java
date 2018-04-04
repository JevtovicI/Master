package jwd.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.model.Igrac;
import jwd.repository.IgracRepository;
import jwd.service.IgracService;

@Service
@Transactional
public class JpaIgracServiceImpl implements IgracService{
	
	@Autowired
	private IgracRepository igracRepository;

	@Override
	public Page<Igrac> findAll(int pageNum) {
		return igracRepository.findAll(
				new PageRequest(pageNum, 6));
	}

	@Override
	public Igrac findOne(Long id) {
		return igracRepository.findOne(id);
	}

	@Override
	public void save(Igrac igrac) {
		igracRepository.save(igrac);
	}

	@Override
	public void remove(Long id) {
		igracRepository.delete(id);
	}

	@Override
	public Page<Igrac> findByTimId(int pageNum, Long timId) {
		
		return igracRepository.findByTimId(timId, new PageRequest(pageNum, 6));
	}
	
	@Override
	public Page<Igrac> pretraga(String imePrezime, Integer broj, Long timId, int page) {
		if(imePrezime != null ){
			imePrezime = "%" + imePrezime + "%";
		}
		
		return igracRepository.pretraga(imePrezime, broj, timId, new PageRequest(page, 6));
	}

	
}
