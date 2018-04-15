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

import bma.model.Department;
import bma.service.DepartmentService;
import bma.support.DepartmentConverter;
import bma.web.dto.DepartmentDTO;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentConverter convert;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DepartmentDTO>> list() {
		List<Department> departments = departmentService.findAll();
		return new ResponseEntity<>(convert.modelListToDto(departments), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody DepartmentDTO depDTO) {
//		Department saved = departmentService.save(convert.dtoToModel(depDTO));
//		return new ResponseEntity<>(convert.modelToDto(saved), HttpStatus.OK);
		departmentService.save(convert.dtoToModel(depDTO));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DepartmentDTO> get(@PathVariable Long id) {
		Department dept = departmentService.getById(id);
		if (dept == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			return new ResponseEntity<>(convert.modelToDto(dept), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		departmentService.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody DepartmentDTO depDTO) {
//		Department edited = departmentService.update(convert.dtoToModel(depDTO));
//		return new ResponseEntity<>(convert.modelToDto(edited), HttpStatus.OK);
		departmentService.update(convert.dtoToModel(depDTO));
	}

}