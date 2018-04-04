package jwd.apoteka.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Apoteka;
import jwd.apoteka.model.Lek;
import jwd.apoteka.model.Proizvodjac;
import jwd.apoteka.service.ApotekaService;
import jwd.apoteka.service.LekService;
import jwd.apoteka.service.ProizvodjacService;
import jwd.apoteka.web.dto.LekDTO;

@Component
public class LekDTOToLek implements Converter<LekDTO, Lek> {

	@Autowired
	private LekService lekService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private ProizvodjacService proizvodjacService;
	
	@Override
	public Lek convert(LekDTO dto) {
		Lek lek;
		if(dto.getId() == null) {
			lek = new Lek();
		} else {
			lek = lekService.findOne(dto.getId());
			
			if(lek == null) {
				throw new IllegalStateException("Editing non-existant Lek");
			}
		}
		
		lek.setNaziv(dto.getNaziv());
		lek.setGenerickiNaziv(dto.getGenerickiNaziv());
		lek.setKolicina(dto.getKolicina());
		
		if (dto.getCena() == null)
			lek.setCena(0.0);
		else 
			lek.setCena(dto.getCena());
		
		Apoteka apoteka = apotekaService.findOne(dto.getApotekaId());
		
		if(apoteka != null) {
			lek.setApoteka(apoteka);
		} else {
			throw new IllegalStateException("Editing non-existant Apoteka");
		}
		
		Proizvodjac proizvodjac = proizvodjacService.findOne(dto.getProizvodjacId());
		
		if(proizvodjac != null) {
			lek.setProizvodjac(proizvodjac);
		} else {
			throw new IllegalStateException("Editing non-existant Proizvodjac");
		}
		
		return lek;
	}

}
