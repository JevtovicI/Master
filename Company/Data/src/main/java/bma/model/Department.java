package bma.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private Long noEmployees;
	// @Column
	// private Employee employee;
	@Column
	@OneToMany(mappedBy = "department", cascade=CascadeType.ALL)
	private List<Employee> employees = new ArrayList<>();
	@ManyToOne(fetch= FetchType.EAGER)
	private Company company;
	

	public Department() {

	}

	public Department(Long id, String name, Long noEmployees, List<Employee> employees, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.noEmployees = noEmployees;
		this.employees = employees;
		this.company = company;
	}

	public Department(Long id, String name, Long noEmployees, List<Employee> employees) {
		super();
		this.id = id;
		this.name = name;
		this.noEmployees = noEmployees;
		this.employees = employees;
	}

	public Department(Long id, String name, Long noEmployees) {
		this.id = id;
		this.name = name;
		this.noEmployees = noEmployees;
	}


	public Department(String name, Long noEmployees) {
		super();
		this.name = name;
		this.noEmployees = noEmployees;
	}

	

	@Override
	public String toString() {
		return "Departman pod nazivom " + name + ", sa brojem zaposlenih " + noEmployees + ", i zaposlenima: "
				+ employees + ", pripada kompaniji :" + company + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNoEmployees() {
		return noEmployees;
	}

	public void setNoEmployees(Long noEmployees) {
		this.noEmployees = noEmployees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void addEmployee(Employee empl) {
		this.employees.add(empl);
	}

}
