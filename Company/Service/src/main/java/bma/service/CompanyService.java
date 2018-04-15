package bma.service;

import java.util.List;

import bma.model.Company;

public interface CompanyService {

	public void delete(Company company);

	void delete(Long id);

	public List<Company> findAll();

	Company findOne(Long id);

	List<Company> getByCity(String city);

	void save(Company company);

	void update(Company company);
}
