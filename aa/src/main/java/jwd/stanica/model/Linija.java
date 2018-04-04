package jwd.stanica.model;

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
public class Linija {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false,unique=true)
	private Long brojMesta;
	@Column
	private Integer cenaKarte;
	@Column(nullable=false)
	private String vremePolaska;
	@Column
	private String destinacija;
	@ManyToOne(fetch=FetchType.EAGER)
	private Prevoznik prevoznik;
	@OneToMany(mappedBy="linija",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Kupovina> kupovine = new ArrayList<>();
	
	public Linija() {
		super();
	}

	
	public Linija(Long id, Long brojMesta, Integer cenaKarte, String vremePolaska, String destinacija,
			Prevoznik prevoznik, List<Kupovina> kupovine) {
		super();
		this.id = id;
		this.brojMesta = brojMesta;
		this.cenaKarte = cenaKarte;
		this.vremePolaska = vremePolaska;
		this.destinacija = destinacija;
		this.prevoznik = prevoznik;
		this.kupovine = kupovine;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getBrojMesta() {
		return brojMesta;
	}


	public void setBrojMesta(Long brojMesta) {
		this.brojMesta = brojMesta;
	}


	public Integer getCenaKarte() {
		return cenaKarte;
	}


	public void setCenaKarte(Integer cenaKarte) {
		this.cenaKarte = cenaKarte;
	}


	public String getVremePolaska() {
		return vremePolaska;
	}


	public void setVremePolaska(String vremePolaska) {
		this.vremePolaska = vremePolaska;
	}


	public String getDestinacija() {
		return destinacija;
	}


	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}


	public Prevoznik getPrevoznik() {
		return prevoznik;
	}
	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
		if(prevoznik!=null && !prevoznik.getLinija().contains(this)){
			prevoznik.getLinija().add(this);
		}
	}
	public List<Kupovina> getKupovine() {
		return kupovine;
	}
	public void setKupovine(List<Kupovina> kupovine) {
		this.kupovine = kupovine;
	}
	public void addKupovina(Kupovina kupovina){
		this.kupovine.add(kupovina);
		
		if(!this.equals(kupovina.getLinija())){
			kupovina.setLinija(this);
		}
	}
}
