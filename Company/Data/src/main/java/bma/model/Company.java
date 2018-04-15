package bma.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String name;
	@Column
	private String country;
	@Column
	private String city;
	@Column
	private String address;
	@Column
	@OneToMany(mappedBy = "company", cascade=CascadeType.ALL)
	private List<Department> departments = new ArrayList<>();

	public Company() {

	}

	public Company(Long id, String name, String country, String city, String address, List<Department> departments) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
		this.departments = departments;
	}

	public Company(String name, String country, String city, String address) {
		super();
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
	}

	public Company(Long id, String name, String country, String city, String address) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Company pod nazivom " + name + ", iz " + country + ", registrovana u gradu " + city + ", sa adresom "
				+ address + ", i departmanima " + departments;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public void addDepartment(Department department) {
		this.departments.add(department);
		
	}

}
