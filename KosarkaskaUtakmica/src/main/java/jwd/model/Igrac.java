package jwd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Igrac {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false)
	private String imePrezime;
	@Column(nullable = false)
	private Integer broj;
	@Column(nullable = false)
	private Integer licneGreske;
	@Column
	private Boolean van;
	@ManyToOne(fetch = FetchType.EAGER)
	private Tim tim;
	@ManyToOne(fetch = FetchType.EAGER)
	private Pozicija pozicija;
	@OneToMany(mappedBy = "igrac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Faul> faulovi = new ArrayList<>();

	public Igrac() {
		super();
	}

	public Igrac(Long id, String imePrezime, Integer broj, Integer licneGreske, Boolean van, Tim tim, Pozicija pozicija,
			List<Faul> faulovi) {
		super();
		this.id = id;
		this.imePrezime = imePrezime;
		this.broj = broj;
		this.licneGreske = licneGreske;
		this.van = van;
		this.tim = tim;
		this.pozicija = pozicija;
		this.faulovi = faulovi;
	}

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

	public Pozicija getPozicija() {
		return pozicija;
	}

	public void setPozicija(Pozicija pozicija) {
		this.pozicija = pozicija;
		if (pozicija != null && !pozicija.getIgraci().contains(this)) {
			pozicija.getIgraci().add(this);
		}
	}

	public Tim getTim() {
		return tim;
	}

	public void setTim(Tim tim) {
		this.tim = tim;
		if (tim != null && !tim.getIgraci().contains(this)) {
			tim.getIgraci().add(this);
		}
	}

	public List<Faul> getFaulovi() {
		return faulovi;
	}

	public void setFaulovi(List<Faul> faulovi) {
		this.faulovi = faulovi;
	}

	public void addFaul(Faul faul) {
		this.faulovi.add(faul);

		if (!this.equals(faul.getIgrac())) {
			faul.setIgrac(this);
		}
	}

	@Override
	public String toString() {
		return "Igrac [van=" + van + "]";
	}
	
}
