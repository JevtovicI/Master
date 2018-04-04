package jwd.pivo.web.controller;

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

import jwd.pivo.model.Pivara;
import jwd.pivo.model.Pivo;
import jwd.pivo.service.PivaraService;
import jwd.pivo.service.PivoService;
import jwd.pivo.support.PivaraToPivaraDTO;
import jwd.pivo.support.PivoToPivoDTO;
import jwd.pivo.web.dto.PivaraDTO;
import jwd.pivo.web.dto.PivoDTO;

@RestController
@RequestMapping(value="/api/pivare")
public class ApiPivaraController {
	@Autowired
	private PivaraService pivaraService;
	@Autowired
	private PivoService pivoService;
	@Autowired
	private PivaraToPivaraDTO toDTO;
	@Autowired
	private PivoToPivoDTO toPivoDTO;
	@Autowired
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PivaraDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(pivaraService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PivaraDTO> get(
			@PathVariable Long id){
		
		Pivara pivara = pivaraService.findOne(id);
		
		if(pivara == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(pivara),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{pivaraId}/piva")
	public ResponseEntity<List<PivoDTO>> pivoPivara(
			@PathVariable Long pivaraId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Pivo> piva = pivoService.findByPivaraId(pageNum, pivaraId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(piva.getTotalPages()) );
		return  new ResponseEntity<>(
				toPivoDTO.convert(piva.getContent()),
				headers,
				HttpStatus.OK);
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ResponseEntity<PivaraDTO> add(
//			@RequestBody @Validated PivaraDTO novaPivara){
//		
//		Pivara pivara = toPivara.convert(novaPivara); 
//		pivaraService.save(pivara);
//		
//		return new ResponseEntity<>(toDTO.convert(pivara),
//				HttpStatus.CREATED);
//	}
}
