package bma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bma.model.Department;
import bma.model.Employee;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query("delete from Department d where d.id=?1")
	void deleteEmployeesByDepartment(Long id);

	Department findByName(String name);

	@Query("select d.noEmployees from Department d")
	Integer totalNumberOfEmployeeByDepartment(Department dep);

	@Query("update Department d set d.employees=?2")
	void updateEmployeeToDifferentDepartment(Department department, Employee emp);

	@Query("update Department d set d.name=?2 where d.id=?1")
	void updateName(Long id, String name);

}
