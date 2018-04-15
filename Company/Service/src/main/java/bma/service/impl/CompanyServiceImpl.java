package bma.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bma.model.Company;
import bma.repository.CompanyRepository;
import bma.service.CompanyService;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public void delete(Company company) {
		companyRepository.delete(company);
		
	}

	@Override
	public void delete(Long id) {
		companyRepository.deleteById(id);
		
	}

	@Override
	public List<Company> findAll() {
		
		return companyRepository.findAll();
	}

	@Override
	public Company findOne(Long id) {
		return companyRepository.getOne(id);
	}

	@Override
	public List<Company> getByCity(String city) {
		
		return companyRepository.getByCity(city);
	}

	@Override
	public void save(Company company) {
		companyRepository.save(company);
		
	}

	@Override
	public void update(Company company) {
		companyRepository.save(company);		
	}

}
