package jwd.web.controller;

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

import jwd.model.Tim;
import jwd.model.Igrac;
import jwd.service.TimService;
import jwd.service.IgracService;
import jwd.support.TimToTimDTO;
import jwd.support.IgracToIgracDTO;
import jwd.web.dto.TimDTO;
import jwd.web.dto.IgracDTO;

@RestController
@RequestMapping(value="/api/timovi")
public class ApiTimController {
	@Autowired
	private TimService timService;
	@Autowired
	private IgracService igracService;
	@Autowired
	private TimToTimDTO toDTO;
	@Autowired
	private IgracToIgracDTO toIgracDTO;
	@Autowired
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TimDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(timService.findAll()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TimDTO> get(
			@PathVariable Long id){
		
		Tim tim = timService.findOne(id);
		
		if(tim == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(tim),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{timId}/igraci")
	public ResponseEntity<List<IgracDTO>> igracTim(
			@PathVariable Long timId,
			@RequestParam(defaultValue="0") int pageNum){
		Page<Igrac> igraci = igracService.findByTimId(pageNum, timId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(igraci.getTotalPages()) );
		return  new ResponseEntity<>(
				toIgracDTO.convert(igraci.getContent()),
				headers,
				HttpStatus.OK);
	}
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ResponseEntity<PivaraDTO> add(
//			@RequestBody @Validated PivaraDTO novaPivara){
//		
//		Pivara tim = toPivara.convert(novaPivara); 
//		timService.save(tim);
//		
//		return new ResponseEntity<>(toDTO.convert(tim),
//				HttpStatus.CREATED);
//	}
}
