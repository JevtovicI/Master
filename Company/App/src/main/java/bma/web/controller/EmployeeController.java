package bma.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bma.model.Employee;
import bma.service.EmployeeService;
import bma.support.EmployeeConverter;
import bma.web.dto.EmployeeDTO;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeConverter convert;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeDTO>> list() {
		List<Employee> employees = employeeService.findAll();
		return new ResponseEntity<>(convert.modelListToDto(employees), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@Valid @RequestBody EmployeeDTO emplDTO) {
//		Employee saved = employeeService.save(convert.dtoToModel(emplDTO));
//		return new ResponseEntity<>(convert.modelToDto(saved), HttpStatus.OK);
		employeeService.save(convert.dtoToModel(emplDTO));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeDTO> get(@PathVariable Long id) {
		Employee empl = employeeService.getById(id);
		if (empl == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(convert.modelToDto(empl), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		employeeService.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO emplDTO) {
//		Employee edited = employeeService.update(convert.dtoToModel(emplDTO));
//		return new ResponseEntity<>(convert.modelToDto(edited), HttpStatus.OK);
		employeeService.update(convert.dtoToModel(emplDTO));
	}

}
