package jwd.stanica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.stanica.model.Kupovina;
import jwd.stanica.model.Linija;
import jwd.stanica.repository.KupovinaRepository;
import jwd.stanica.repository.LinijaRepository;
import jwd.stanica.service.KupovinaService;

@Service
public class JpaKupovinaServiceImpl implements KupovinaService{
	
	@Autowired
	private KupovinaRepository kupovinaRepository;
	@Autowired
	private LinijaRepository linijaRepository;
	
	@Override
	public Kupovina buyALine(Long linijaId) {
		
		if(linijaId == null) {
			throw new IllegalArgumentException("Id of a line cannot be null!");
		}
		
		Linija linija = linijaRepository.findOne(linijaId);
		if(linija == null) {
			throw new IllegalArgumentException("There is no line with given id!");
		}
		
		if(linija.getBrojMesta() > 0) {
			
			Kupovina kupovina = new Kupovina();
			kupovina.setLinija(linija);
			
			linija.setBrojMesta(linija.getBrojMesta() - 1);
			
			kupovinaRepository.save(kupovina);
			linijaRepository.save(linija);
			
			return kupovina;
		}
		
		return null;
		
	}
}
