package jwd.apoteka.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tblApoteka")
public class Apoteka {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String ime;
	
	@Column
	private String adresa;
	
	@OneToMany(mappedBy="apoteka", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Lek> lekovi = new ArrayList<>();

	public Apoteka() {
		super();
	}

	public Apoteka(String ime, String adresa) {
		super();
		this.ime = ime;
		this.adresa = adresa;
	}

	public Apoteka(Long id, String ime, String adresa) {
		super();
		this.id = id;
		this.ime = ime;
		this.adresa = adresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public List<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Lek> lekovi) {
		this.lekovi = lekovi;
	}
	
	public void addLek(Lek lek) {
		this.lekovi.add(lek);
		
		if(!this.equals(lek.getApoteka())) {
			lek.setApoteka(this);
		}
	}
	
	public void removeLek(Lek lek){
		lek.setApoteka(null);
		lekovi.remove(lek);
	}
	
	
}
