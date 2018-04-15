package bma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		
	
		
		SpringApplication.run(App.class, args);
//		
//		Long nextId = 0L;
//		List<Company> companies = new ArrayList<>();
//
//		Company company1 = new Company(nextId++, "Bright Marbles", "Srbija", "Novi Sad", "Danila Kisa");
//		Company company2 = new Company(nextId++, "Levi 9", "Srbija", "Novi Sad", "Trg Marije Trandafil");
//		Company company3 = new Company(nextId++, "Namics", "Srbija", "Beograd", "Bulevar Kralja Aleksandra");
//		{
//
//			companies.add(company1);
//			companies.add(company2);
//			companies.add(company3);
//
//		}
		
//		@SuppressWarnings("resource")
//		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//		CompanyService service = appContext.getBean("companyService", CompanyService.class);
//		DepartmentService dService = appContext.getBean("departmentService", DepartmentService.class);
//		EmployeeService eService = appContext.getBean("employeeService", EmployeeService.class);
//
//		Company c1 = new Company("Naovis", "Srbija", "Novi Sad", "negde u Novome Sadu");
//		service.save(c1);
//
//		service.delete(1L);
//
//		service.findAll();
//		service.getByCity("Beograd");
//
//		dService.findAll();
//		Department department = new Department("lezilebovici", 1L);
//		dService.save(department);
//		dService.findAll();
//		dService.delete(3L);
//		dService.findByName("R&D");
//		dService.updateName(2L, "pokusaj");
//		Department dep = dService.findAll().get(0);
//		dService.totalNumberOfEmployeeByDepartment(dep);
//
//		eService.findAll();
//		Employee emp = new Employee("Mirko", "063896523", "mirko@gmail", new BigDecimal(90000.00));
//		eService.save(emp);
//		eService.getEmployeeWithHighestSalary();
//		eService.delete(3L);

	}
}