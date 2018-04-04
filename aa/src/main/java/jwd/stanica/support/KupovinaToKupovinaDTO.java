package jwd.stanica.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Kupovina;
import jwd.stanica.web.dto.KupovinaDTO;

@Component
public class KupovinaToKupovinaDTO implements Converter<Kupovina, KupovinaDTO> {

	@Override
	public KupovinaDTO convert(Kupovina arg0) {
		
		KupovinaDTO dto = new KupovinaDTO();
		dto.setId(arg0.getId());
		
		
		return dto;
	}

}
