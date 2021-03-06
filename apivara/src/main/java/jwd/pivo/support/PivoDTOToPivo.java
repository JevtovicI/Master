package jwd.pivo.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.pivo.model.Pivo;
import jwd.pivo.service.PivaraService;
import jwd.pivo.service.PivoService;
import jwd.pivo.web.dto.PivoDTO;

@Component
public class PivoDTOToPivo 
	implements Converter<PivoDTO, Pivo>{
	
	@Autowired
	private PivoService pivoService;
	@Autowired
	private PivaraService pivaraService;
	
	
	@Override
	public Pivo convert(PivoDTO source) {
		Pivo pivo;
		if(source.getId()==null){
			pivo = new Pivo();
			pivo.setPivara(
					pivaraService.findOne(
							source.getPivaraId()));
		}else{
			pivo = pivoService.findOne(source.getId());
		}
		
		pivo.setNaziv(source.getNaziv());
		pivo.setVrsta(source.getVrsta());
		pivo.setProcenatAlkohola(source.getProcenatAlkohola());
		pivo.setIbu(source.getIbu());
		pivo.setKolicina(source.getKolicina());
		
		return pivo;
	}

}
