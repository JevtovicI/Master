package jwd.apoteka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Proizvodjac;
import jwd.apoteka.web.dto.ProizvodjacDTO;

@Component
public class ProizvodjacToProizvodjacDTO implements Converter<Proizvodjac, ProizvodjacDTO> {

	@Override
	public ProizvodjacDTO convert(Proizvodjac proizvodjac) {
		ProizvodjacDTO dto = new ProizvodjacDTO();
		
		dto.setId(proizvodjac.getId());
		dto.setIme(proizvodjac.getIme());
		
		return dto;
	}
	
	public List<ProizvodjacDTO> convert(List<Proizvodjac> p) {
		List<ProizvodjacDTO> dtos = new ArrayList<>();
		
		for(Proizvodjac pr : p)
			dtos.add(convert(pr));
		
		return dtos;
	}

}
