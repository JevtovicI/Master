package bma.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import bma.model.Company;
import bma.web.dto.CompanyDTO;

@Component
public class CompanyConverter implements GenericConverter<CompanyDTO, Company> {

	@Override
	public CompanyDTO modelToDto(Company t2) {
		CompanyDTO dto = new CompanyDTO(t2.getId(), t2.getName(), t2.getCountry(), t2.getCity(), t2.getAddress());

		return dto;
	}

	@Override
	public Company dtoToModel(CompanyDTO t1) {
		Company mod = new Company(t1.getId(), t1.getName(), t1.getCountry(), t1.getCity(), t1.getAddress());

		return mod;
	}

	@Override
	public List<CompanyDTO> modelListToDto(List<Company> t2s) {
		List<CompanyDTO> ret = new ArrayList<>();

		for (Company c : t2s) {
			ret.add(modelToDto(c));
		}

		return ret;
	}

}
