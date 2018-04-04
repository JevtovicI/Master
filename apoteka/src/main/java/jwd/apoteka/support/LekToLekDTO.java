package jwd.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Lek;
import jwd.apoteka.web.dto.LekDTO;

@Component
public class LekToLekDTO implements Converter<Lek, LekDTO> {

	@Override
	public LekDTO convert(Lek lek) {
		LekDTO dto = new LekDTO();
		
		dto.setId(lek.getId());
		dto.setNaziv(lek.getNaziv());
		dto.setGenerickiNaziv(lek.getGenerickiNaziv());
		dto.setKolicina(lek.getKolicina());
		dto.setCena(lek.getCena());
		dto.setApotekaId(lek.getApoteka().getId());
		dto.setApotekaIme(lek.getApoteka().getIme());
		dto.setProizvodjacId(lek.getProizvodjac().getId());
		dto.setProizvodjacIme(lek.getProizvodjac().getIme());
		
		return dto;
	}
	
	public List<LekDTO> convert(List<Lek> lekovi) {
		List<LekDTO> dtos = new ArrayList<>();
		
		for(Lek l : lekovi)
			dtos.add(convert(l));
		
		return dtos;
	}

}
