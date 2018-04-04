package jwd.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.model.Faul;
import jwd.web.dto.FaulDTO;

@Component
public class FaulToFaulDTO implements Converter<Faul, FaulDTO> {

	@Override
	public FaulDTO convert(Faul arg0) {
		
		FaulDTO dto = new FaulDTO();
		dto.setId(arg0.getId());
		
		
		return dto;
	}

}
