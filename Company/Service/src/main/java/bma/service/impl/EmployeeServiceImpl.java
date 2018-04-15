package bma.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bma.model.Employee;
import bma.repository.EmployeeRepository;
import bma.service.EmployeeService;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);

	}

	@Override
	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	@Override
	public BigDecimal getEmployeeWithHighestSalary(List<Employee> employees) {
		return employeeRepository.getEmployeeWithHighestSalary(employees);
	}

	@Override
	public Employee getById(Long id) {
		return employeeRepository.getOne(id);
	}

	@Override
	public void update(Employee emp) {
		employeeRepository.save(emp);

	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Employee employee = employeeRepository.findByUsername(username);
//
//        if (employee == null) {
//            throw new UsernameNotFoundException(username + " was not found");
//        }
//System.out.println(new String[employee.getRoles().size()]);
//        return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(),
//                AuthorityUtils.createAuthorityList(employee.getRoles().toArray(new String[employee.getRoles().size()])));
//	}

}
