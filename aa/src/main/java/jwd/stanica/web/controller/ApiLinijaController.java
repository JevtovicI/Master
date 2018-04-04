package jwd.stanica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
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

import jwd.stanica.model.Kupovina;
import jwd.stanica.model.Linija;
import jwd.stanica.service.KupovinaService;
import jwd.stanica.service.LinijaService;
import jwd.stanica.support.KupovinaToKupovinaDTO;
import jwd.stanica.support.LinijaDTOToLinija;
import jwd.stanica.support.LinijaToLinijaDTO;
import jwd.stanica.web.dto.KupovinaDTO;
import jwd.stanica.web.dto.LinijaDTO;

@RestController
@RequestMapping("/api/linije")
public class ApiLinijaController {
	@Autowired
	private LinijaService linijaService;
	@Autowired
	private KupovinaService kupovinaService;
	@Autowired
	private LinijaToLinijaDTO toLinijaDTO;
	@Autowired
	private KupovinaToKupovinaDTO toKupovinaDTO;
	@Autowired
	private LinijaDTOToLinija toLinija;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LinijaDTO>> get(
			@RequestParam(required=false) String destinacija,
			@RequestParam(required=false) Long prevoznikId,
			@RequestParam(required=false) Integer maxC,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Linija> linije;
		
		if(destinacija != null || prevoznikId != null || maxC != null) {
			linije = linijaService.pretraga(destinacija, prevoznikId, maxC, pageNum);
		}else{
			linije = linijaService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linije.getTotalPages()) );
		return  new ResponseEntity<>(
				toLinijaDTO.convert(linije.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<LinijaDTO> get(
			@PathVariable Long id){
		Linija linija = linijaService.findOne(id);
		
		if(linija==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toLinijaDTO.convert(linija),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<LinijaDTO> add(
			@RequestBody @Validated LinijaDTO novaLinija){
		
		Linija linija = toLinija.convert(novaLinija); 
		linijaService.save(linija);
		
		return new ResponseEntity<>(toLinijaDTO.convert(linija),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
	public ResponseEntity<KupovinaDTO> buy(@PathVariable Long id){
		
		Kupovina k = kupovinaService.buyALine(id);
		
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<LinijaDTO> edit(
			@PathVariable Long id,
			@RequestBody @Validated LinijaDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Linija linija = toLinija.convert(izmenjen); 
		linijaService.save(linija);
		
		return new ResponseEntity<>(toLinijaDTO.convert(linija),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<LinijaDTO> delete(@PathVariable Long id){
		
		Linija linija = linijaService.findOne(id);
		if(linija != null) {
			linijaService.remove(id);
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
