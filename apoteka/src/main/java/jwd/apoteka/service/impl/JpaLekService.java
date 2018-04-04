package jwd.apoteka.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.apoteka.model.Lek;
import jwd.apoteka.repository.LekRepository;
import jwd.apoteka.service.LekService;

@Service
@Transactional
public class JpaLekService implements LekService {

	@Autowired
	private LekRepository lekRepository;
	
	@Override
	public Page<Lek> findAll(int pageNum) {
		return lekRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Lek findOne(Long id) {
		return lekRepository.findOne(id);
	}

	@Override
	public Lek save(Lek lek) {
		return lekRepository.save(lek);
	}

	@Override
	public void delete(Long id) {
		lekRepository.delete(id);
	}

	@Override
	public Page<Lek> findByApotekaId(Long apotekaId, int page) {
		return lekRepository.findByApotekaId(apotekaId, new PageRequest(page, 5));
	}

	@Override
	public Lek findByIdAndApotekaId(Long id, Long apotekaId) {
		return lekRepository.findByIdAndApotekaId(id, apotekaId);
	}

//	@Override
//	public Page<Lek> findByApotekaIdOrNazivLikeOrGenerickiNazivLike(
//			Long apotekaId, String naziv, String generickiNaziv, int page) {
//		if(naziv != null) {
//			naziv = "%" + naziv + "%";
//		}
//		
//		if(generickiNaziv != null) {
//			generickiNaziv = "%" + generickiNaziv + "%";
//		} 
		
//		if (naziv == null && generickiNaziv == null) {
//			return lekRepository.findByApotekaId(apotekaId, new PageRequest(page, 5));
//		}
//		
//		return lekRepository.findByApotekaIdOrNazivLikeOrGenerickiNazivLike(
//				apotekaId, 
//				"%" + naziv + "%",
//				"%" + generickiNaziv + "%",
//				new PageRequest(page, 5));
//	}

//	@Override
//	public Page<Lek> pretraga(Long apotekaId, String naziv, int page) {
//		if(naziv != null) {
//			naziv = "%" + naziv + "%";
//		}
//		return lekRepository.pretraga(apotekaId, naziv, new PageRequest(page, 5));
//	}

	@Override
	public Page<Lek> findByApotekaIdOrNazivOrGenerickiNaziv(
			Long apotekaId, 
			String naziv, 
			String generickiNaziv,
			int page) {
		return lekRepository.findByApotekaIdOrNazivOrGenerickiNaziv(apotekaId, naziv, generickiNaziv, new PageRequest(page, 5));
	}

}
