package jwd.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.model.Tim;
import jwd.repository.TimRepository;
import jwd.service.TimService;

@Service
@Transactional
public class JpaTimServiceImpl implements TimService {
	@Autowired
	private TimRepository timRepository;

	@Override
	public List<Tim> findAll() {
		return timRepository.findAll();
	}

	@Override
	public Tim findOne(Long id) {
		return timRepository.findOne(id);
	}

	@Override
	public void save(Tim tim) {
		timRepository.save(tim);
	}

	@Override
	public void remove(Long id) {
		timRepository.delete(id);
	}

	
	
}
