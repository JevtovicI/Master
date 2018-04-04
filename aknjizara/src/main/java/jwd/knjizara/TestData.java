package jwd.knjizara;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.knjizara.model.Knjiga;
import jwd.knjizara.model.Izdavac;
import jwd.knjizara.service.KnjigaService;
import jwd.knjizara.service.IzdavacService;


@Component
public class TestData {
	@Autowired
	private IzdavacService izdavacService;
	@Autowired
	private KnjigaService knjigaService;

	@PostConstruct
	public void init() {
		
		Izdavac p1 = new Izdavac();
		p1.setNaziv("Delfi");
		p1.setTelefon("12121212");
		p1.setAdresa("Beograd");
		izdavacService.save(p1);
		
		Izdavac p2 = new Izdavac();
		p2.setNaziv("Samizdat");
		p2.setTelefon("32132111");
		p2.setAdresa("Novi Sad");
		izdavacService.save(p2);
				
		Knjiga l1 = new Knjiga();
		l1.setNaziv("Tako je govorio Zaratustra");
		l1.setIzdanje(2000);
		l1.setPisac("Fridrih Nice");
		l1.setIsbn("2016-14-26");
//		l1.setBrojGlasova(0);
		l1.setIzdavac(p1);
		knjigaService.save(l1);
	}
}