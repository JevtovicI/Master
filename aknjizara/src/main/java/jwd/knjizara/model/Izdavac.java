package jwd.knjizara.model;

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
@Table
public class Izdavac {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column // (nullable=false,unique=true)
	private String naziv;
	@Column // (unique=true)
	private String adresa;
	@Column
	private String telefon;
	@OneToMany(mappedBy = "izdavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Knjiga> knjiga = new ArrayList<>();

	public Izdavac() {
		super();
	}

	public Izdavac(Long id, String naziv, String adresa, String telefon, List<Knjiga> knjiga) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.knjiga = knjiga;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Knjiga> getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(List<Knjiga> knjiga) {
		this.knjiga = knjiga;
	}

	public void addKnjiga(Knjiga knjiga) {
		this.knjiga.add(knjiga);

		if (!this.equals(knjiga.getIzdavac())) {
			knjiga.setIzdavac(this);
		}
	}

}
