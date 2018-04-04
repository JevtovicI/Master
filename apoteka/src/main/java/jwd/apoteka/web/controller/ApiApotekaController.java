package jwd.apoteka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.apoteka.model.Apoteka;
import jwd.apoteka.model.Lek;
import jwd.apoteka.service.ApotekaService;
import jwd.apoteka.service.LekService;
import jwd.apoteka.support.ApotekaToApotekaDTO;
import jwd.apoteka.support.LekDTOToLek;
import jwd.apoteka.support.LekToLekDTO;
import jwd.apoteka.web.dto.ApotekaDTO;
import jwd.apoteka.web.dto.LekDTO;

@RestController
@RequestMapping(value="/api/apoteke")
public class ApiApotekaController {
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private ApotekaToApotekaDTO toDTO;
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private LekToLekDTO toDto;
	
	@Autowired
	private LekDTOToLek toLek;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ApotekaDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(apotekaService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id_apoteke}/lekovi", method = RequestMethod.GET)
	public ResponseEntity<List<LekDTO>> get(
			@PathVariable Long id_apoteke,
			@RequestParam(defaultValue="0") int page) {
		Page<Lek> lekPages = lekService.findByApotekaId(id_apoteke, page);
		
		return new ResponseEntity<>(toDto.convert(lekPages.getContent()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id_apoteke}/lekovi/{id}", method = RequestMethod.GET)
	public ResponseEntity<LekDTO> get(@PathVariable Long id,
			@PathVariable Long id_apoteke) {

		Lek lek = lekService.findByIdAndApotekaId(id, id_apoteke);

		if (lek == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(lek), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id_apoteke}/lekovi", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<LekDTO> add(
			@RequestBody @Validated LekDTO newLek,
			@PathVariable Long id_apoteke) {

		Lek lek = toLek.convert(newLek);
		Apoteka apoteka = apotekaService.findOne(id_apoteke);
		apoteka.addLek(lek);

		Lek persisted = lekService.save(lek);
		apotekaService.save(apoteka);

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/{id_apoteke}/lekovi/{id}", 
			method = RequestMethod.PUT, 
			consumes = "application/json")
	public ResponseEntity<LekDTO> edit(
			@RequestBody @Validated LekDTO editedLek,
			@PathVariable Long id_apoteke,
			@PathVariable Long id,
			@RequestParam(required=false, value="kolicina") Integer kolicina) {

		if (id == null || !id.equals(editedLek.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

//		Lek oldLek = lekService.findByIdAndApotekaId(id, id_apoteke);
		Lek oldLek = lekService.findOne(id);
		
		if (oldLek == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(kolicina != null) {
			if (kolicina > editedLek.getKolicina()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			editedLek.setKolicina(editedLek.getKolicina() - kolicina);
		}
		
		Lek persisted = lekService.save(toLek.convert(editedLek));

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id_apoteke}/lekovi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LekDTO> delete(
			@PathVariable Long id_apoteke,
			@PathVariable Long id) {

		Apoteka apoteka = apotekaService.findOne(id_apoteke);
		Lek lek = lekService.findOne(id);
		
		if (lek == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		apoteka.removeLek(lek);

		lekService.delete(id);
		apotekaService.save(apoteka);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
