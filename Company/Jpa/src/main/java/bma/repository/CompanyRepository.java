package bma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bma.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	List<Company> getByCity(String city);

}
