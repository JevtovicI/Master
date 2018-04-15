package bma.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bma.model.Department;
import bma.model.Employee;
import bma.repository.DepartmentRepository;
import bma.service.DepartmentService;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public void delete(Long id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public void deleteEmployeesByDepartment(Long id) {
		departmentRepository.deleteEmployeesByDepartment(id);
	}

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Department findByName(String name) {
		return departmentRepository.findByName(name);
	}

	@Override
	public Department getById(Long id) {
		return departmentRepository.getOne(id);
	}

	@Override
	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Integer totalNumberOfEmployeeByDepartment(Department dep) {
		return departmentRepository.totalNumberOfEmployeeByDepartment(dep);
	}

	@Override
	public void update(Department dep) {
		departmentRepository.save(dep);
	}

	 @Override
	 public void updateEmployeeToDifferentDepartment(Department department,	Employee emp) {
	 departmentRepository.updateEmployeeToDifferentDepartment(department, emp);
	
	 }

	@Override
	public void updateName(Long id, String name) {
		departmentRepository.updateName(id, name);

	}

}
