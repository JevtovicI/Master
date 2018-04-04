package jwd.knjizara.web.controller;

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

import jwd.knjizara.model.Glasanje;
import jwd.knjizara.model.Knjiga;
import jwd.knjizara.service.GlasanjeService;
import jwd.knjizara.service.KnjigaService;
import jwd.knjizara.support.GlasanjeToGlasanjeDTO;
import jwd.knjizara.support.KnjigaDTOToKnjiga;
import jwd.knjizara.support.KnjigaToKnjigaDTO;
import jwd.knjizara.web.dto.GlasanjeDTO;
import jwd.knjizara.web.dto.KnjigaDTO;

@RestController
@RequestMapping("/api/knjige")
public class ApiKnjigaController {
	@Autowired
	private KnjigaService knjigaService;
	@Autowired
	private GlasanjeService glasanjeService;
	@Autowired
	private KnjigaToKnjigaDTO toKnjigaDTO;
	@Autowired
	private GlasanjeToGlasanjeDTO toGlasanjeDTO;
	@Autowired
	private KnjigaDTOToKnjiga toKnjiga;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<KnjigaDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String pisac,
			@RequestParam(required=false) Integer minG,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Knjiga> knjige;
		
		if(naziv != null || pisac != null || minG != null) {
			knjige = knjigaService.pretraga(naziv, pisac, minG, pageNum);
		}else{
			knjige = knjigaService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(knjige.getTotalPages()) );
		return  new ResponseEntity<>(
				toKnjigaDTO.convert(knjige.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<KnjigaDTO> get(
			@PathVariable Long id){
		Knjiga knjiga = knjigaService.findOne(id);
		
		if(knjiga==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toKnjigaDTO.convert(knjiga),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<KnjigaDTO> add(
			@RequestBody @Validated KnjigaDTO novaKnjiga){
		
		Knjiga knjiga = toKnjiga.convert(novaKnjiga); 
		knjigaService.save(knjiga);
		
		return new ResponseEntity<>(toKnjigaDTO.convert(knjiga),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/glasanje")
	public ResponseEntity<GlasanjeDTO> vote(@PathVariable Long id){
		
		Glasanje g = glasanjeService.buyAGlas(id);
		
		
			return new ResponseEntity<>(toGlasanjeDTO.convert(g), HttpStatus.CREATED);
		
		
	}
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<KnjigaDTO> edit(
			@PathVariable Long id,
			@RequestBody @Validated KnjigaDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Knjiga knjiga = toKnjiga.convert(izmenjen); 
		knjigaService.save(knjiga);
		
		return new ResponseEntity<>(toKnjigaDTO.convert(knjiga),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<KnjigaDTO> delete(@PathVariable Long id){
		
		Knjiga knjiga = knjigaService.findOne(id);
		if(knjiga != null) {
			knjigaService.remove(id);
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
