package jwd.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.model.Igrac;
import jwd.model.Pozicija;
import jwd.model.Tim;
import jwd.service.TimService;
import jwd.service.IgracService;
import jwd.service.PozicijaService;
import jwd.web.dto.IgracDTO;

@Component
public class IgracDTOToIgrac 
	implements Converter<IgracDTO, Igrac>{
	
	@Autowired
	private IgracService igracService;
	@Autowired
	private TimService timService;
	@Autowired
	private PozicijaService pozicijaService;
	
	
	@Override
	public Igrac convert(IgracDTO source) {
		Igrac igrac;
		Tim t = timService.findOne(source.getTimId());
		Pozicija p = pozicijaService.findOne(source.getPozicijaId());
		
		if(source.getId()==null){
			igrac = new Igrac();
			igrac.setLicneGreske(0);
		
		}else{
			igrac = igracService.findOne(source.getId());
			igrac.setLicneGreske(source.getLicneGreske());
		}
		igrac.setTim(t);
		igrac.setPozicija(p);
		igrac.setImePrezime(source.getImePrezime());
		igrac.setBroj(source.getBroj());
		igrac.setVan(source.getVan());
		
		return igrac;
	}

}
