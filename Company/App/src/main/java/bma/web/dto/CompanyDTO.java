package bma.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyDTO {

	private Long id;
	@NotBlank
	private String name;
	private String country;
	@NotBlank
	@Size(max = 40)
	private String city;
	@NotBlank
	@Size(max = 60)
	private String address;

	
	
	public CompanyDTO() {
	}

	public CompanyDTO(Long id, @NotBlank String name, String country, @NotBlank @Size(max = 40) String city,
			@NotBlank @Size(max = 60) String address) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
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

}
