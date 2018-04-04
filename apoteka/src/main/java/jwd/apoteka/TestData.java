package jwd.apoteka;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.apoteka.model.Apoteka;
import jwd.apoteka.model.Lek;
import jwd.apoteka.model.Proizvodjac;
import jwd.apoteka.service.ApotekaService;
import jwd.apoteka.service.LekService;
import jwd.apoteka.service.ProizvodjacService;

@Component
public class TestData {

	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private ProizvodjacService proizvodjacService;
	
	@Autowired
	private LekService lekService;
	

	@PostConstruct
	public void init(){
	    	   Apoteka apoteka1 = new Apoteka();
	    	   apoteka1.setIme("Apoteka1");
	    	   apoteka1.setAdresa("Adresa1");
	    	   apotekaService.save(apoteka1);
	    	   
	    	   Apoteka apoteka2 = new Apoteka();
	    	   apoteka2.setIme("Apoteka2");
	    	   apoteka2.setAdresa("Adresa2");
	    	   apotekaService.save(apoteka2);
	    	   
	    	   Proizvodjac p1 = new Proizvodjac();
	    	   p1.setIme("Proizvodjac1");
	    	   proizvodjacService.save(p1);
	    	   
	    	   Proizvodjac p2 = new Proizvodjac();
	    	   p2.setIme("Proizvodjac2");
	    	   proizvodjacService.save(p2);

	            	Lek lek1 = new Lek();
	            	lek1.setNaziv("Lek1");
	            	lek1.setKolicina(100);
	            	lek1.setCena(900.5);
	            	lek1.setGenerickiNaziv("Genericki naziv1");
	            	lek1.setApoteka(apoteka1);
	            	apoteka1.addLek(lek1);
	            	lek1.setProizvodjac(p1);
	            	p1.addLek(lek1);
	            	
	            	Lek lek2 = new Lek();
	            	lek2.setNaziv("Lek2");
	            	lek2.setKolicina(500);
	            	lek2.setCena(100.25);
	            	lek2.setGenerickiNaziv("Genericki naziv2");
	            	lek2.setApoteka(apoteka2);
	            	apoteka2.addLek(lek2);
	            	lek2.setProizvodjac(p2);
	            	p2.addLek(lek2);
	            	
	            	Lek lek3 = new Lek();
	            	lek3.setNaziv("Lek3");
	            	lek3.setKolicina(900);
	            	lek3.setCena(860.0);
	            	lek3.setGenerickiNaziv("Genericki naziv3");
	            	lek3.setApoteka(apoteka2);
	            	apoteka2.addLek(lek3);
	            	lek3.setProizvodjac(p2);
	            	p2.addLek(lek3);

	            	lekService.save(lek1);
	            	lekService.save(lek2);
	            	lekService.save(lek3);
	
		
	}
}
