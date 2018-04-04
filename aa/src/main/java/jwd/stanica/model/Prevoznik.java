package jwd.stanica.model;

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
public class Prevoznik {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false,unique=true)
	private String naziv;
	@Column(unique=true)
	private String adresa;
	@Column
	private String pib;
	@OneToMany(mappedBy="prevoznik",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Linija> linija = new ArrayList<>();
	
	
	public Prevoznik() {
		super();
	}
	

	public Prevoznik(Long id, String naziv, String adresa, String pib, List<Linija> linija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.pib = pib;
		this.linija = linija;
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


	public String getPib() {
		return pib;
	}


	public void setPib(String pib) {
		this.pib = pib;
	}


	public List<Linija> getLinija() {
		return linija;
	}


	public void setLinija(List<Linija> linija) {
		this.linija = linija;
	}
	public void addLinija(Linija linija){
		this.linija.add(linija);
		
		if(!this.equals(linija.getPrevoznik())){
			linija.setPrevoznik(this);
		}
	}
	
	
}
