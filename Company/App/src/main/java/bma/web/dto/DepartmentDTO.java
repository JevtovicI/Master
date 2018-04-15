package bma.web.dto;

import javax.validation.constraints.NotBlank;

public class DepartmentDTO {

	private Long id;
	@NotBlank
	private String name;

	private Long noEmployees;

	public DepartmentDTO() {
		super();
	}

	public DepartmentDTO(Long id, @NotBlank String name, Long noEmployees) {
		super();
		this.id = id;
		this.name = name;
		this.noEmployees = noEmployees;
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

}
