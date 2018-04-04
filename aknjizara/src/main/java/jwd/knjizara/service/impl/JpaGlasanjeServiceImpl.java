package jwd.knjizara.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.knjizara.model.Glasanje;
import jwd.knjizara.model.Knjiga;
import jwd.knjizara.repository.GlasanjeRepository;
import jwd.knjizara.repository.KnjigaRepository;
import jwd.knjizara.service.GlasanjeService;

@Service
public class JpaGlasanjeServiceImpl implements GlasanjeService{
	
	@Autowired
	private GlasanjeRepository glasanjeRepository;
	@Autowired
	private KnjigaRepository knjigaRepository;
	
	@Override
	public Glasanje buyAGlas(Long knjigaId) {
		
		if(knjigaId == null) {
			throw new IllegalArgumentException("Id of a book cannot be null!");
		}
		
		Knjiga knjiga = knjigaRepository.findOne(knjigaId);
		if(knjiga == null) {
			throw new IllegalArgumentException("There is no book with given id!");
		}
		
//		if(knjiga.getBrojGlasova() < 0) {
			
			Glasanje glasanje = new Glasanje();
			glasanje.setKnjiga(knjiga);
			
			knjiga.setBrojGlasova(knjiga.getBrojGlasova() + 1);
			
			glasanjeRepository.save(glasanje);
			knjigaRepository.save(knjiga);
			
			return glasanje;
//		}
		
//		return null;
		
	}
}
