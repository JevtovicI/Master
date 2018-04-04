package jwd.knjizara.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.knjizara.model.Knjiga;
import jwd.knjizara.repository.KnjigaRepository;
import jwd.knjizara.service.KnjigaService;

@Service
@Transactional
public class JpaKnjigaServiceImpl implements KnjigaService {

	@Autowired
	private KnjigaRepository knjigaRepository;

	@Override
	public Page<Knjiga> findAll(int pageNum) {
		return knjigaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Knjiga findOne(Long id) {
		return knjigaRepository.findOne(id);
	}

	@Override
	public void save(Knjiga linija) {
		knjigaRepository.save(linija);
	}

	@Override
	public void remove(Long id) {
		knjigaRepository.delete(id);
	}

	@Override
	public Page<Knjiga> findByIzdavacId(int pageNum, Long izdavacId) {

		return knjigaRepository.findByIzdavacId(izdavacId, new PageRequest(pageNum, 5));
	}

	@Override
	public Page<Knjiga> pretraga(String naziv, String pisac, Integer minG, int page) {
		if (naziv != null) {
			naziv = "%" + naziv + "%";
		} else if (pisac != null) {
			pisac = "%" + pisac + "%";
		}

		return knjigaRepository.pretraga(naziv, pisac, minG, new PageRequest(page, 5));
	}

}
