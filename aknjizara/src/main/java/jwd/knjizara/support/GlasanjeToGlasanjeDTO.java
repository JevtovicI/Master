package jwd.knjizara.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.knjizara.model.Glasanje;
import jwd.knjizara.web.dto.GlasanjeDTO;

@Component
public class GlasanjeToGlasanjeDTO implements Converter<Glasanje, GlasanjeDTO> {

	@Override
	public GlasanjeDTO convert(Glasanje arg0) {
		
		GlasanjeDTO dto = new GlasanjeDTO();
		dto.setId(arg0.getId());
		
		
		return dto;
	}

}
