package jwd.pivo.web.controller;

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

import jwd.pivo.model.Kupovina;
import jwd.pivo.model.Pivo;
import jwd.pivo.service.KupovinaService;
import jwd.pivo.service.PivoService;
import jwd.pivo.support.KupovinaToKupovinaDTO;
import jwd.pivo.support.PivoDTOToPivo;
import jwd.pivo.support.PivoToPivoDTO;
import jwd.pivo.web.dto.KupovinaDTO;
import jwd.pivo.web.dto.PivoDTO;

@RestController
@RequestMapping("/api/piva")
public class ApiPivoController {
	@Autowired
	private PivoService pivoService;
	@Autowired
	private KupovinaService kupovinaService;
	@Autowired
	private PivoToPivoDTO toPivoDTO;
	@Autowired
	private KupovinaToKupovinaDTO toKupovinaDTO;
	@Autowired
	private PivoDTOToPivo toPivo;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PivoDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Float minIbu,
			@RequestParam(required=false) Float maxIbu,
			@RequestParam(required=false) Long pivaraId,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Pivo> piva;
		
		if(naziv != null || minIbu != null || maxIbu != null || pivaraId !=null) {
			piva = pivoService.pretraga(naziv, minIbu, maxIbu, pivaraId, pageNum);
		}else{
			piva = pivoService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(piva.getTotalPages()) );
		return  new ResponseEntity<>(
				toPivoDTO.convert(piva.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<PivoDTO> get(
			@PathVariable Long id){
		Pivo pivo = pivoService.findOne(id);
		
		if(pivo==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toPivoDTO.convert(pivo),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PivoDTO> add(
			@RequestBody @Validated PivoDTO novoPivo){
		
		Pivo pivo = toPivo.convert(novoPivo); 
		pivoService.save(pivo);
		
		return new ResponseEntity<>(toPivoDTO.convert(pivo),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
	public ResponseEntity<KupovinaDTO> buy(@PathVariable Long id){
		
		Kupovina k = kupovinaService.buyAPivo(id);
		
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<PivoDTO> edit(
			@PathVariable Long id,
			@RequestBody @Validated PivoDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Pivo pivo = toPivo.convert(izmenjen); 
		pivoService.save(pivo);
		
		return new ResponseEntity<>(toPivoDTO.convert(pivo),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<PivoDTO> delete(@PathVariable Long id){
		
		Pivo pivo = pivoService.findOne(id);
		if(pivo != null) {
			pivoService.remove(id);
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
