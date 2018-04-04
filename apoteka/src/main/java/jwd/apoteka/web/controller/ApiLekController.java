package jwd.apoteka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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
import jwd.apoteka.support.LekDTOToLek;
import jwd.apoteka.support.LekToLekDTO;
import jwd.apoteka.web.dto.LekDTO;

@RestController
@RequestMapping("/api/lekovi")
public class ApiLekController {
	
	@Autowired
	private LekService lekService;
	
	@Autowired
	private LekToLekDTO toDto;
//	
//	@Autowired
//	private LekDTOToLek toLek;
//	
//	@Autowired
//	private ApotekaService apotekaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<LekDTO>> get(
			@RequestParam(required=false, value="apotekaId") Long apotekaId,
			@RequestParam(required=false, value="naziv") String naziv,
			@RequestParam(defaultValue="0") int page) {
		Page<Lek> lekPages;
		
		if (apotekaId != null || naziv != null) {
			lekPages = lekService.findByApotekaIdOrNazivOrGenerickiNaziv(apotekaId, naziv, naziv, page);
			
			if(lekPages.getNumberOfElements() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			lekPages = lekService.findAll(page);
		}
		
		if (page > lekPages.getTotalPages()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(lekPages.getTotalPages()) );
		return new ResponseEntity<>(toDto.convert(lekPages.getContent()), headers, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<LekDTO> get(@PathVariable Long id,
//			@PathVariable Long id_apoteke) {
//
//		Lek lek = lekService.findByIdAndApotekaId(id, id_apoteke);
//
//		if (lek == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity<>(toDto.convert(lek), HttpStatus.OK);
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//	public ResponseEntity<LekDTO> add(
//			@RequestBody @Validated LekDTO newLek,
//			@PathVariable Long id_apoteke) {
//
//		Lek lek = toLek.convert(newLek);
//		Apoteka apoteka = apotekaService.findOne(id_apoteke);
//		apoteka.addLek(lek);
//
//		Lek persisted = lekService.save(lek);
//		apotekaService.save(apoteka);
//
//		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.CREATED);
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
//	public ResponseEntity<LekDTO> edit(@PathVariable Long id,
//			@RequestBody @Validated LekDTO editedLek,
//			@PathVariable Long id_apoteke) {
//
//		if (id == null || !id.equals(editedLek.getId())) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//
//		Lek oldLek = lekService.findByIdAndApotekaId(id, id_apoteke);
//		if (oldLek == null) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//
//		Lek persisted = lekService.save(toLek.convert(editedLek));
//
//		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<LekDTO> delete(@PathVariable Long id,
//			@PathVariable Long id_apoteke) {
//
//		Apoteka apoteka = apotekaService.findOne(id_apoteke);
//		Lek lek = lekService.findOne(id);
//		
//		if (lek == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//		apoteka.removeLek(lek);
//
//		lekService.delete(id);
//		apotekaService.save(apoteka);
//
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}

}
