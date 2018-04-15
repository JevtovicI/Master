package bma.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bma.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select max(e.salary) from Employee e")	
	BigDecimal getEmployeeWithHighestSalary(List<Employee> employees);

//	Employee findByUsername(String username);
}
