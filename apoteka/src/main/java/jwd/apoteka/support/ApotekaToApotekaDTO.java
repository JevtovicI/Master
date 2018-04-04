package jwd.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Apoteka;
import jwd.apoteka.web.dto.ApotekaDTO;

@Component
public class ApotekaToApotekaDTO implements Converter<Apoteka, ApotekaDTO> {

	@Override
	public ApotekaDTO convert(Apoteka apoteka) {
		ApotekaDTO dto = new ApotekaDTO();
		
		dto.setId(apoteka.getId());
		dto.setIme(apoteka.getIme());
		dto.setAdresa(apoteka.getAdresa());
		
		return dto;
	}
	
	public List<ApotekaDTO> convert(List<Apoteka> apoteke) {
		List<ApotekaDTO> dtos = new ArrayList<>();
		
		for(Apoteka a : apoteke)
			dtos.add(convert(a));
		
		return dtos;
	}

}
