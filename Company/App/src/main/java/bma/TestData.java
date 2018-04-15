package bma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import bma.model.Company;
import bma.model.Department;
import bma.model.Employee;
import bma.model.Role;
import bma.repository.UserRepository;
import bma.service.CompanyService;
import bma.service.DepartmentService;
import bma.service.EmployeeService;
import bma.service.RoleService;

// Inicijalni podaci za bazu
@Component
public class TestData {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRepository users;

	@PostConstruct
	public void init() {
		Company company1 = new Company("BrightMarbles", "Serbia", "Novi Sad", "Danila Kisa 3");
		Company company2 = new Company("Synechron", "Serbia", "Novi Sad", "Vojvodjanskih Brigada 28");
		Company company3 = new Company("Coing", "Serbia", "Beograd", "Laze Kostica 17");

		companyService.save(company1);
		companyService.save(company2);
		companyService.save(company3);

		Department department1 = new Department("Marketing", 5L);
		Department department2 = new Department("Kontroling", 6L);
		Department department3 = new Department("Finansije", 4L);

		department1.setCompany(company1);
		department2.setCompany(company1);
		department3.setCompany(company1);

		departmentService.save(department1);
		departmentService.save(department2);
		departmentService.save(department3);

		Role role1 = new Role("ROLE_USER");
		Role role2 = new Role("ROLE_ADMIN");

		roleService.save(role1);
		roleService.save(role2);

		// List<Role> roles = new ArrayList<>();
		// roles.add(role1);
		// roles.add(role2);

		Employee employee1 = new Employee("Petar Petrovic", "065/515-56-98", "petar@gmail.com", new BigDecimal(80000),
				"root", "root1");
//		role1.getEmployees().add(employee1);
		Employee employee2 = new Employee("Petar Jovic", "065/515-56-99", "petarj@gmail.com", new BigDecimal(100000),
				"pera", "password");
		Employee employee3 = new Employee("Dragan Dragic", "065/515-57-98", "dragan@gmail.com", new BigDecimal(90000),
				"dragance", "password");

		employee1.setDepartment(department1);
		employee2.setDepartment(department1);
		employee3.setDepartment(department1);

		// role1.setEmployees([employee1, ]);

//		Role role1 = new Role("ROLE_USER");
//		Role role2 = new Role("ROLE_ADMIN");
		
		role1.getEmployees().add(employee1);
		role1.addEmployee(employee2);
		 role1.addEmployee(employee3 );
		 role2.addEmployee(employee3);

		 roleService.save(role1);
		 roleService.save(role2);

		employeeService.save(employee1);
		employeeService.save(employee2);
		employeeService.save(employee3);

		// users.save(new Employee("perica", new Role("ROLE_USER")));
		// users.save(employee2);
		// users.save(employee3);

	}
}
