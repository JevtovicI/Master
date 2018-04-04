package jwd.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.model.Faul;
import jwd.model.Igrac;
import jwd.service.FaulService;
import jwd.service.IgracService;
import jwd.support.FaulToFaulDTO;
import jwd.support.IgracDTOToIgrac;
import jwd.support.IgracToIgracDTO;
import jwd.web.dto.FaulDTO;
import jwd.web.dto.IgracDTO;

@RestController
@RequestMapping("/api/igraci")
public class ApiIgracController {
	@Autowired
	private IgracService igracService;
	@Autowired
	private FaulService faulService;
	@Autowired
	private IgracToIgracDTO toIgracDTO;
	@Autowired
	private FaulToFaulDTO toFaulDTO;
	@Autowired
	private IgracDTOToIgrac toIgrac;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<IgracDTO>> get(
			@Param("imePrezime") String imePrezime,
			@Param("broj") Integer broj,
			@Param("timId") Long timId,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Igrac> igraci;
		
		if(imePrezime != null || broj != null || timId !=null) {
			igraci = igracService.pretraga(imePrezime, broj, timId, pageNum);
		}else{
			igraci = igracService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(igraci.getTotalPages()) );
		return  new ResponseEntity<>(
				toIgracDTO.convert(igraci.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<IgracDTO> get(
			@PathVariable Long id){
		Igrac igrac = igracService.findOne(id);
		
		if(igrac==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toIgracDTO.convert(igrac),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<IgracDTO> add(
			@RequestBody @Validated IgracDTO noviIgrac){
		
		Igrac igrac = toIgrac.convert(noviIgrac); 
		igracService.save(igrac);
		
		return new ResponseEntity<>(toIgracDTO.convert(igrac),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/faul")
	public ResponseEntity<FaulDTO> buy(@PathVariable Long id){
		
		Faul f = faulService.makeFaul(id);
		
		if(f == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(toFaulDTO.convert(f), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<IgracDTO> edit(
			@PathVariable Long id,
			@RequestBody @Validated IgracDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Igrac igrac = toIgrac.convert(izmenjen); 
		igracService.save(igrac);
		
		return new ResponseEntity<>(toIgracDTO.convert(igrac),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<IgracDTO> delete(@PathVariable Long id){
		
		Igrac igrac = igracService.findOne(id);
		if(igrac != null) {
			igracService.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
