package jwd.pivo.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.pivo.model.Pivara;
import jwd.pivo.web.dto.PivaraDTO;

@Component
public class PivaraToPivaraDTO 
	implements Converter<Pivara, PivaraDTO> {

	@Override
	public PivaraDTO convert(Pivara pivara) {
		
		PivaraDTO pivaraDTO = new PivaraDTO();
		pivaraDTO.setId(pivara.getId());
		pivaraDTO.setNaziv(pivara.getNaziv());
		pivaraDTO.setDrzava(pivara.getDrzava());
		pivaraDTO.setPib(pivara.getPIB());
		
		return pivaraDTO;
	}
	
	public List<PivaraDTO> convert(List<Pivara> pivare){
		List<PivaraDTO> ret = new ArrayList<>();
		
		for(Pivara p : pivare){
			ret.add(convert(p));
		}
		
		return ret;
	}
}
