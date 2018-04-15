package bma.repository;

import org.springframework.data.repository.CrudRepository;

import bma.model.Employee;

public interface UserRepository extends CrudRepository<Employee, Long> {

	Employee findByUsername(String username);

}
