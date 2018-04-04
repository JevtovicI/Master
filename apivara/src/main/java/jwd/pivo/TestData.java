package jwd.pivo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.pivo.model.Pivara;
import jwd.pivo.model.Pivo;
import jwd.pivo.service.PivaraService;
import jwd.pivo.service.PivoService;


@Component
public class TestData {
	@Autowired
	private PivaraService pivaraService;
	@Autowired
	private PivoService pivoService;

	@PostConstruct
	public void init() {
		
		Pivara p1 = new Pivara();
		p1.setNaziv("Apatinska");
		p1.setPIB("12121212");
		p1.setDrzava("Srbija");
		pivaraService.save(p1);
		
		Pivara p2 = new Pivara();
		p2.setNaziv("Celarevo");
		p2.setPIB("32132111");
		p2.setDrzava("Srbija");
		pivaraService.save(p2);
				
		Pivo l1 = new Pivo();
		l1.setNaziv("Jelen");
		l1.setPivara(p1);
		l1.setVrsta("svetlo");
		l1.setProcenatAlkohola((float) 5.00);
		l1.setIbu((float) 11.10);
		l1.setKolicina(25);
		pivoService.save(l1);
	}
}