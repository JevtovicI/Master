package jwd.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.model.Igrac;
import jwd.model.Pozicija;
import jwd.service.PozicijaService;
import jwd.support.PozicijaToPozicijaDTO;
import jwd.web.dto.IgracDTO;
import jwd.web.dto.PozicijaDTO;


@RestController
@RequestMapping(value="/api/pozicije")
public class ApiPozicijaController {

	
	@Autowired
	private PozicijaService pozicijaService;
	@Autowired
	private PozicijaToPozicijaDTO toDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PozicijaDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(pozicijaService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PozicijaDTO> get(
			@PathVariable Long id){
		
		Pozicija pozicija = pozicijaService.findOne(id);
		
		if(pozicija == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(pozicija),
				HttpStatus.OK);
	}
	
//	@RequestMapping(value="/{pozicijaId}/igraci")
//	public ResponseEntity<List<IgracDTO>> igracPozicija(
//			@PathVariable Long timId,
//			@RequestParam(defaultValue="0") int pageNum){
//		Page<Igrac> igraci = igracService.findByTimId(pageNum, timId);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("totalPages", Integer.toString(igraci.getTotalPages()) );
//		return  new ResponseEntity<>(
//				toIgracDTO.convert(igraci.getContent()),
//				headers,
//				HttpStatus.OK);
//	}
	
}
