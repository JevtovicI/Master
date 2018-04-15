package bma.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "emp_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "employee_id") })
	private List<Employee> employees = new ArrayList<>();

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role(Long id, String name, List<Employee> employees) {
		this.id = id;
		this.name = name;
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setEmployees(Employee employee) {
	this.employees.add(employee);
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addEmployee(Employee empl) {
		this.employees.add(empl);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", employees=" + employees + "]";
	}

}
