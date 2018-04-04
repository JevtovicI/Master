package jwd;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.model.Tim;
import jwd.model.Igrac;
import jwd.model.Pozicija;
import jwd.service.TimService;
import jwd.service.IgracService;
import jwd.service.PozicijaService;


@Component
public class TestData {
	@Autowired
	private TimService timService;
	@Autowired
	private IgracService igracService;
	@Autowired
	private PozicijaService pozicijaService;

	@PostConstruct
	public void init() {
		
		Tim t1 = new Tim();
		t1.setIme("Sloga");
		t1.setTrener("Misko");
		t1.setBoje("plava");
		timService.save(t1);
		
		Tim t2 = new Tim();
		t2.setIme("okk");
		t2.setTrener("Mladjo");
		t2.setBoje("Srbija");
		timService.save(t2);
		
		Pozicija p1 = new Pozicija();
		p1.setNaziv("krilo");
		pozicijaService.save(p1);
		
				
		Igrac l1 = new Igrac();
		l1.setImePrezime("Jovan");
		l1.setTim(t1);
		l1.setBroj(45);
		l1.setLicneGreske(0);
		l1.setVan(false);
		l1.setPozicija(p1);
		igracService.save(l1);
	}
}