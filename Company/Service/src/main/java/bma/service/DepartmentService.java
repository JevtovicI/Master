package bma.service;

import java.util.List;

import bma.model.Department;
import bma.model.Employee;

public interface DepartmentService {

	void delete(Long id);

	void deleteEmployeesByDepartment(Long id);

	List<Department> findAll();

	Department findByName(String name);

	Department getById(Long id);

	Department save(Department department);

	Integer totalNumberOfEmployeeByDepartment(Department dep);

	void update(Department dep);

	void updateEmployeeToDifferentDepartment(Department department,  Employee emp);

	void updateName(Long id, String name);

}
