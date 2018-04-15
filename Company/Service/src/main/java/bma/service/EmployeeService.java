package bma.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import bma.model.Employee;

public interface EmployeeService {

	void save(Employee employee);

	void delete(Long id);

	List<Employee> findAll();

	Employee getById(Long id);

	void update(Employee emp);

	BigDecimal getEmployeeWithHighestSalary(List<Employee> employees);
	
}
