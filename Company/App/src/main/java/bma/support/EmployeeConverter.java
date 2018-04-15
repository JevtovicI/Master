package bma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import bma.model.Employee;
import bma.web.dto.EmployeeDTO;

@Component
public class EmployeeConverter implements GenericConverter<EmployeeDTO, Employee> {

	@Override
	public EmployeeDTO modelToDto(Employee t2) {
		EmployeeDTO dto = new EmployeeDTO(t2.getId(), t2.getFullName(), t2.getPhone(), t2.getEmail(), t2.getSalary(), t2.getUsername(), t2.getPassword());

		return dto;
	}

	@Override
	public Employee dtoToModel(EmployeeDTO t1) {
		Employee mod = new Employee(t1.getId(), t1.getFullName(), t1.getPhone(), t1.getEmail(), t1.getSalary());

		return mod;
	}

	@Override
	public List<EmployeeDTO> modelListToDto(List<Employee> t2s) {
		List<EmployeeDTO> ret = new ArrayList<>();

		for (Employee e : t2s) {
			ret.add(modelToDto(e));
		}

		return ret;
	}

}
