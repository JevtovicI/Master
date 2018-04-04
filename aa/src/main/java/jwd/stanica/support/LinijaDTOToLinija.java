package jwd.stanica.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.stanica.service.PrevoznikService;
import jwd.stanica.model.Linija;
import jwd.stanica.service.LinijaService;
import jwd.stanica.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija 
	implements Converter<LinijaDTO, Linija>{
	
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private PrevoznikService prevoznikService;
	
	
	@Override
	public Linija convert(LinijaDTO source) {
		Linija linija;
		if(source.getId()==null){
			linija = new Linija();
			linija.setPrevoznik(
					prevoznikService.findOne(
							source.getPrevoznikId()));
		}else{
			linija = linijaService.findOne(source.getId());
		}
		
		linija.setBrojMesta(source.getBrojMesta());
		linija.setCenaKarte(source.getCenaKarte());
		linija.setDestinacija(source.getDestinacija());
		linija.setVremePolaska(source.getVremePolaska());
		
		return linija;
	}

}
