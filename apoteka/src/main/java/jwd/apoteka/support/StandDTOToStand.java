package jwd.apoteka.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Sajam;
import jwd.apoteka.model.Stand;
import jwd.apoteka.service.SajamService;
import jwd.apoteka.service.StandService;
import jwd.apoteka.web.dto.StandDTO;

@Component
public class StandDTOToStand 
	implements Converter<StandDTO, Stand>{
	
	@Autowired
	private StandService standService;
	@Autowired
	private SajamService sajamService;
	
	
	@Override
	public Stand convert(StandDTO dto) {
		Stand stand;
		// if slucaj kada dobijam post(unosim novi stand) http request,
		// else slucaj kada dobijam put(menjam postojeci stand) http request
		if(dto.getId()==null) {
			stand = new Stand();
		} else {
			stand = standService.findOne(dto.getId());
			if(stand == null) {
				throw new IllegalStateException("Editing non-existant Stand");
			}
		}
		
		Sajam sajam = sajamService.findOne(dto.getSajamId());
		if (sajam != null) {
			stand.setSajam(sajam);
		} else {
			throw new IllegalStateException("Editing non-existant Sajam");
		}
		
		stand.setPovrsina(dto.getPovrsina());
		stand.setZakupac(dto.getZakupac());
		
		return stand;
	}

}
