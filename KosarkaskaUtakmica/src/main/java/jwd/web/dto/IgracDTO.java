package jwd.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class IgracDTO {

	private Long id;
	@NotBlank
	@NotEmpty
	private String imePrezime;
	@Min(1)
	@Max(99)
	private Integer broj;
	@Min(0)
	@Max(5)
	private Integer licneGreske;
	private Boolean van;	
	private Long timId;
	private String timIme;	
	private Long pozicijaId;
	private String pozicijaNaziv;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public Integer getBroj() {
		return broj;
	}
	public void setBroj(Integer broj) {
		this.broj = broj;
	}
	public Integer getLicneGreske() {
		return licneGreske;
	}
	public void setLicneGreske(Integer licneGreske) {
		this.licneGreske = licneGreske;
	}
	public Boolean getVan() {
		return van;
	}
	public void setVan(Boolean van) {
		this.van = van;
	}
	public Long getTimId() {
		return timId;
	}
	public void setTimId(Long timId) {
		this.timId = timId;
	}
	public String getTimIme() {
		return timIme;
	}
	public void setTimIme(String timIme) {
		this.timIme = timIme;
	}
	public Long getPozicijaId() {
		return pozicijaId;
	}
	public void setPozicijaId(Long pozicijaId) {
		this.pozicijaId = pozicijaId;
	}
	public String getPozicijaNaziv() {
		return pozicijaNaziv;
	}
	public void setPozicijaNaziv(String pozicijaNaziv) {
		this.pozicijaNaziv = pozicijaNaziv;
	}


}
