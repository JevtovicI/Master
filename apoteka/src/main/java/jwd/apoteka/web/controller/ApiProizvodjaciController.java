package jwd.apoteka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.apoteka.service.ProizvodjacService;
import jwd.apoteka.support.ProizvodjacToProizvodjacDTO;
import jwd.apoteka.web.dto.ProizvodjacDTO;

@RestController
@RequestMapping(value="/api/proizvodjaci")
public class ApiProizvodjaciController {

	@Autowired
	private ProizvodjacService proizvodjacService;
	
	@Autowired
	private ProizvodjacToProizvodjacDTO toDto;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProizvodjacDTO>> get(){
		return new ResponseEntity<>(
				toDto.convert(proizvodjacService.findAll()),
				HttpStatus.OK);
	}
}
