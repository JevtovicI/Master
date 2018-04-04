package jwd.stanica.web.controller;

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

import jwd.stanica.service.PrevoznikService;
import jwd.stanica.model.Linija;
import jwd.stanica.model.Prevoznik;
import jwd.stanica.service.LinijaService;
import jwd.stanica.support.PrevoznikToPrevoznikDTO;
import jwd.stanica.support.LinijaToLinijaDTO;
import jwd.stanica.support.PrevoznikDTOToPrevoznik;
import jwd.stanica.web.dto.PrevoznikDTO;
import jwd.stanica.web.dto.LinijaDTO;

@RestController
@RequestMapping(value="/api/prevoznici")
public class ApiPrevoznikController {
	@Autowired
	private PrevoznikService prevoznikService;
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private PrevoznikToPrevoznikDTO toDTO;
	@Autowired
	private LinijaToLinijaDTO toLinijaDTO;
	@Autowired
	private PrevoznikDTOToPrevoznik toPrevoznik;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PrevoznikDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(prevoznikService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PrevoznikDTO> get(
			@PathVariable Long id){
		
		Prevoznik prevoznik = prevoznikService.findOne(id);
		
		if(prevoznik == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(prevoznik),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{prevoznikId}/linije")
	public ResponseEntity<List<LinijaDTO>> linijaPrevoznik(
			@PathVariable Long prevoznikId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Linija> linije = linijaService.findByPrevoznikId(pageNum, prevoznikId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()) );
		return  new ResponseEntity<>(
				toLinijaDTO.convert(linije.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PrevoznikDTO> add(
			@RequestBody @Validated PrevoznikDTO noviPrevoznik){
		
		Prevoznik prevoznik = toPrevoznik.convert(noviPrevoznik); 
		prevoznikService.save(prevoznik);
		
		return new ResponseEntity<>(toDTO.convert(prevoznik),
				HttpStatus.CREATED);
	}
}
