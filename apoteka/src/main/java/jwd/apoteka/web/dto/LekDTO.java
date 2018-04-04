package jwd.apoteka.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class LekDTO {

	private Long id;
	
	@NotBlank
	private String naziv;
	@NotBlank
	private String generickiNaziv;
	@Min(0)
	@Max(1000)
	private Integer kolicina;
	@Min(0)
	private Double cena;
	private Long apotekaId;
	private String apotekaIme;
	private Long proizvodjacId;
	private String proizvodjacIme;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getGenerickiNaziv() {
		return generickiNaziv;
	}
	public void setGenerickiNaziv(String generickiNaziv) {
		this.generickiNaziv = generickiNaziv;
	}
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public Long getApotekaId() {
		return apotekaId;
	}
	public void setApotekaId(Long apotekaId) {
		this.apotekaId = apotekaId;
	}
	public String getApotekaIme() {
		return apotekaIme;
	}
	public void setApotekaIme(String apotekaIme) {
		this.apotekaIme = apotekaIme;
	}
	public Long getProizvodjacId() {
		return proizvodjacId;
	}
	public void setProizvodjacId(Long proizvodjacId) {
		this.proizvodjacId = proizvodjacId;
	}
	public String getProizvodjacIme() {
		return proizvodjacIme;
	}
	public void setProizvodjacIme(String proizvodjacIme) {
		this.proizvodjacIme = proizvodjacIme;
	}
	
	
}
