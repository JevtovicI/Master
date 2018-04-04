package jwd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.model.Faul;
import jwd.model.Igrac;
import jwd.repository.FaulRepository;
import jwd.repository.IgracRepository;
import jwd.service.FaulService;

@Service
public class JpaFaulServiceImpl implements FaulService{
	
	@Autowired
	private FaulRepository faulRepository;
	@Autowired
	private IgracRepository igracRepository;
	
	@Override
	public Faul makeFaul(Long igracId) {
		
		if(igracId == null) {
			throw new IllegalArgumentException("Id of a player cannot be null!");
		}
		
		Igrac igrac = igracRepository.findOne(igracId);
		if(igrac == null) {
			throw new IllegalArgumentException("There is no player with given id!");
		}
		
		if(igrac.getLicneGreske() == 0 || igrac.getLicneGreske() < 5) {
			
			Faul faul = new Faul();
			faul.setIgrac(igrac);
			
			igrac.setLicneGreske(igrac.getLicneGreske() + 1);
			
			faulRepository.save(faul);
			igracRepository.save(igrac);
			System.out.println(igrac);
			
			return faul;
		}else {
			
		igrac.setVan(true);
		
		System.out.println(igrac);
		
		return null;
	}
	}
}
