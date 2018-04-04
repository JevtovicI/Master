package jwd.stanica.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.LinijaService;
import jwd.stanica.service.PrevoznikService;
import jwd.stanica.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDTOToPrevoznik implements Converter<PrevoznikDTO, Prevoznik> {

	@Autowired
	private LinijaService linijaService;
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Override
	public Prevoznik convert(PrevoznikDTO source) {
		Prevoznik prevoznik;
		if(source.getId()==null){
			prevoznik = new Prevoznik();
			
		}else{
			prevoznik = prevoznikService.findOne(source.getId());
		}
		prevoznik.setAdresa(source.getAdresa());
		prevoznik.setNaziv(source.getNaziv());
		prevoznik.setPib(source.getPib());		
		
		return prevoznik;
	}

	
}
