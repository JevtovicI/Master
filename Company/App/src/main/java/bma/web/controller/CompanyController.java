package bma.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bma.model.Company;
import bma.service.CompanyService;
import bma.support.CompanyConverter;
import bma.web.dto.CompanyDTO;

@RestController
@RequestMapping("api/company")
public class CompanyController {

	@Autowired
	private CompanyService companyDao;
	@Autowired
	private CompanyConverter convert;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CompanyDTO>> list() {
		List<Company> companies = companyDao.findAll();
		return new ResponseEntity<>(convert.modelListToDto(companies), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody CompanyDTO compDTO) {
		// Company saved = companyDao.save(convert.dtoToModel(compDTO));
		// return new ResponseEntity<>(convert.modelToDto(saved), HttpStatus.OK);
		companyDao.save(convert.dtoToModel(compDTO));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CompanyDTO> get(@PathVariable Long id) {
		Company comp = companyDao.findOne(id);
		if (comp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(convert.modelToDto(comp), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		companyDao.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody CompanyDTO compDTO) {
		// Company edited = companyDao.update(convert.dtoToModel(compDTO));
		// return new ResponseEntity<>(convert.modelToDto(edited), HttpStatus.OK);
		companyDao.update(convert.dtoToModel(compDTO));
	}

}
