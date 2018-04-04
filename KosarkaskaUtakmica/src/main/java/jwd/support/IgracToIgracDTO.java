package jwd.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.model.Igrac;
import jwd.web.dto.IgracDTO;

@Component
public class IgracToIgracDTO 
	implements Converter<Igrac, IgracDTO> {

	@Override
	public IgracDTO convert(Igrac source) {
		IgracDTO dto = new IgracDTO();
		
		dto.setId(source.getId());
		dto.setImePrezime(source.getImePrezime());
		dto.setBroj(source.getBroj());
		dto.setLicneGreske(source.getLicneGreske());
		dto.setVan(source.getVan());
		
//		if(source.getTim() != null || source.getPozicija() !=null) 
//		{
			dto.setTimId(source.getTim().getId());
			dto.setTimIme(source.getTim().getIme());
			dto.setPozicijaId(source.getPozicija().getId());
			dto.setPozicijaNaziv(source.getPozicija().getNaziv());
//		}
//		else if(source.getPozicija() !=null) {
//			dto.setPozicijaId(source.getPozicija().getId());
//			dto.setPozicijaNaziv(source.getPozicija().getNaziv());
//		}
		
		
		return dto;
	}
	
	public List<IgracDTO> convert(List<Igrac> igraci){
		List<IgracDTO> ret = new ArrayList<>();
		
		for(Igrac i : igraci){
			ret.add(convert(i));
		}
		
		return ret;
	}

}
