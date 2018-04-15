package bma.web.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import bma.web.dto.validator.ContactNumberConstraint;

public class EmployeeDTO {

	private Long id;
	@NotBlank
	private String fullName;
	@ContactNumberConstraint
	private String phone;
	@Email
	private String email;
	@PositiveOrZero
	private BigDecimal salary;
	private String username;
	private String password;

	public EmployeeDTO(Long id, @NotBlank String fullName, String phone, @Email String email,
			@PositiveOrZero BigDecimal salary) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
	}

	public EmployeeDTO(Long id, @NotBlank String fullName, String phone, @Email String email,
			@PositiveOrZero BigDecimal salary, String username, String password) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.username = username;
		this.password = password;
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
		this.password = password;
	}

	public EmployeeDTO() {
		// TODO Auto-generated constructor stub
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

}
