package jwd.knjizara.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.knjizara.model.Izdavac;
import jwd.knjizara.service.KnjigaService;
import jwd.knjizara.service.IzdavacService;
import jwd.knjizara.web.dto.IzdavacDTO;

@Component
public class IzdavacDTOToIzdavac implements Converter<IzdavacDTO, Izdavac> {

	@Autowired
	private KnjigaService knjigaService;
	@Autowired
	private IzdavacService izdavacService;
	
	@Override
	public Izdavac convert(IzdavacDTO source) {
		Izdavac izdavac;
		if(source.getId()==null){
			izdavac = new Izdavac();
			
		}else{
			izdavac = izdavacService.findOne(source.getId());
		}
		izdavac.setAdresa(source.getAdresa());
		izdavac.setNaziv(source.getNaziv());
		izdavac.setTelefon(source.getTelefon());		
		
		return izdavac;
	}

	
}
