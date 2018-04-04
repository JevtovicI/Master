package jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.model.Tim;
import jwd.web.dto.TimDTO;

@Component
public class TimToTimDTO 
	implements Converter<Tim, TimDTO> {

	@Override
	public TimDTO convert(Tim tim) {
		
		TimDTO timDTO = new TimDTO();
		timDTO.setId(tim.getId());
		timDTO.setIme(tim.getIme());
		timDTO.setTrener(tim.getTrener());
		timDTO.setBoje(tim.getBoje());
		
		return timDTO;
	}
	
	public List<TimDTO> convert(List<Tim> timovi){
		List<TimDTO> ret = new ArrayList<>();
		
		for(Tim t : timovi){
			ret.add(convert(t));
		}
		
		return ret;
	}
}
