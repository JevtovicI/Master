package jwd.stanica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.stanica.model.Prevoznik;
import jwd.stanica.model.Linija;
import jwd.stanica.service.PrevoznikService;
import jwd.stanica.service.LinijaService;


@Component
public class TestData {
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private LinijaService linijaService;

	
	
	@PostConstruct
	public void init() {
		
		Prevoznik p1 = new Prevoznik();
		p1.setNaziv("Apatinska");
		p1.setPib("12121212");
		p1.setAdresa("apatin");
		prevoznikService.save(p1);
		
		Prevoznik p2 = new Prevoznik();
		p2.setNaziv("Celarevo");
		p2.setPib("32132111");
		p2.setAdresa("novi sad");
		prevoznikService.save(p2);
				
		Linija l1 = new Linija();
		l1.setBrojMesta(50L);
		l1.setPrevoznik(p1);
		l1.setCenaKarte(600);
		l1.setDestinacija("destinacija");
		l1.setVremePolaska("vremePolaska");
		linijaService.save(l1);
	}
}