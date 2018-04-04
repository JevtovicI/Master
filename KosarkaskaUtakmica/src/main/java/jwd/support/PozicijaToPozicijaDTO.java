package jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.model.Pozicija;
import jwd.web.dto.PozicijaDTO;


@Component
public class PozicijaToPozicijaDTO 
	implements Converter<Pozicija, PozicijaDTO> {

	@Override
	public PozicijaDTO convert(Pozicija pozicija) {
		
		PozicijaDTO pozicijaDTO = new PozicijaDTO();
		pozicijaDTO.setId(pozicija.getId());
		pozicijaDTO.setNaziv(pozicija.getNaziv());
		
		return pozicijaDTO;
	}
	
	public List<PozicijaDTO> convert(List<Pozicija> pozicije){
		List<PozicijaDTO> ret = new ArrayList<>();
		
		for(Pozicija p : pozicije){
			ret.add(convert(p));
		}
		
		return ret;
	}
}
