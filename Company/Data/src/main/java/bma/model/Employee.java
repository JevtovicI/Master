package bma.model;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "employee")
public class Employee {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String fullName;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private BigDecimal salary;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@ManyToMany (cascade= {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "emp_role", 
	joinColumns= {@JoinColumn(name = "employee_id")}, 
	inverseJoinColumns = {@JoinColumn (name = "role_id")})
	private List<Role> roles = new ArrayList<>();
	
	@ManyToOne(fetch= FetchType.EAGER)
	private Department department;

	public Employee() {

	}

	public Employee(Long id, String fullName, String phone, String email, BigDecimal salary) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
	}

	public Employee(Long id, String fullName, String phone, String email, BigDecimal salary, Department department) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.department = department;
	}

	public Employee(String fullName, String phone, String email, BigDecimal salary) {
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
	}

	public Employee(String fullName, String phone, String email, BigDecimal salary, String username, String password,
			List<Role> roles) {
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.username = username;
		setPassword(password);
		this.roles = roles;
	}

	public Employee(String fullName, String phone, String email, BigDecimal salary, String username, String password) {
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.username = username;
		setPassword(password);
	}

	

	public Employee(String username, Role role) {
		this.username = username;
		this.addRole(role);
	}

	@Override
	public String toString() {
		return "Zaposleni sa Imenom i prezimenom" + fullName + ",  brojem telefona" + phone + ", email adresom " + email
				+ ", prima platu u iznosu od: " + salary + ", i pripada departmanu: " + department + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
		
	}

}
