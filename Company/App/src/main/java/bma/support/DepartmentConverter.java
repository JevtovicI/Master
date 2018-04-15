package bma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import bma.model.Department;
import bma.web.dto.DepartmentDTO;

@Component
public class DepartmentConverter implements GenericConverter<DepartmentDTO, Department> {

	@Override
	public DepartmentDTO modelToDto(Department t2) {
		DepartmentDTO dto = new DepartmentDTO(t2.getId(), t2.getName(), t2.getNoEmployees());

		return dto;
	}

	@Override
	public Department dtoToModel(DepartmentDTO t1) {
		Department mod = new Department(t1.getId(), t1.getName(), t1.getNoEmployees());

		return mod;
	}

	@Override
	public List<DepartmentDTO> modelListToDto(List<Department> t2s) {
		List<DepartmentDTO> ret = new ArrayList<>();

		for (Department d : t2s) {
			ret.add(modelToDto(d));
		}

		return ret;
	}

}
