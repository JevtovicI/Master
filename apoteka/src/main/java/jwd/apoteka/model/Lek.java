package jwd.apoteka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tblLek")
public class Lek {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String naziv;
	
	@Column(nullable=false)
	private String generickiNaziv;
	
	@Column(nullable=false)
	private Integer kolicina;
	
	@Column(nullable=false)
	private Double cena;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Apoteka apoteka;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Proizvodjac proizvodjac;

	public Lek() {
		super();
	}

	public Lek(String naziv, String generickiNaziv, Integer kolicina, Double cena, Apoteka apoteka,
			Proizvodjac proizvodjac) {
		super();
		this.naziv = naziv;
		this.generickiNaziv = generickiNaziv;
		this.kolicina = kolicina;
		this.cena = cena;
		this.apoteka = apoteka;
		this.proizvodjac = proizvodjac;
	}

	public Lek(Long id, String naziv, String generickiNaziv, Integer kolicina, Double cena, Apoteka apoteka,
			Proizvodjac proizvodjac) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.generickiNaziv = generickiNaziv;
		this.kolicina = kolicina;
		this.cena = cena;
		this.apoteka = apoteka;
		this.proizvodjac = proizvodjac;
	}

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

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
		
		if(apoteka != null && !apoteka.getLekovi().contains(this)) {
			apoteka.getLekovi().add(this);
		}
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
		
		if(proizvodjac != null && !proizvodjac.getLekovi().contains(this)) {
			proizvodjac.getLekovi().add(this);
		}
	}
	
	
	
}
