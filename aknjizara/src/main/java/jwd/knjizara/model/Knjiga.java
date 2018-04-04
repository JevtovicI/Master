package jwd.knjizara.model;

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
public class Knjiga {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false)
	private String naziv;
	@Column (nullable=false)
	private Integer izdanje;
	@Column(nullable=false)
	private String pisac;
	@Column(nullable=false, unique=true)
	private String isbn;
	@Column
	private Integer brojGlasova;
	@ManyToOne(fetch=FetchType.EAGER)
	private Izdavac izdavac;
	@OneToMany(mappedBy="knjiga",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Glasanje> glasanje = new ArrayList<>();
	
	
	public Knjiga() {
		this.brojGlasova = 0;
	}

	
	public Knjiga(Long id, String naziv, Integer izdanje, String pisac, String isbn, Integer brojGlasova,
			Izdavac izdavac, List<Glasanje> glasanje) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.izdanje = izdanje;
		this.pisac = pisac;
		this.isbn = isbn;
		this.brojGlasova = brojGlasova;
		this.izdavac = izdavac;
		this.glasanje = glasanje;
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


	public Integer getIzdanje() {
		return izdanje;
	}


	public void setIzdanje(Integer izdanje) {
		this.izdanje = izdanje;
	}


	public String getPisac() {
		return pisac;
	}


	public void setPisac(String pisac) {
		this.pisac = pisac;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public Integer getBrojGlasova() {
		return brojGlasova;
	}


	public void setBrojGlasova(Integer brojGlasova) {
		this.brojGlasova = brojGlasova;
	}


	public Izdavac getIzdavac() {
		return izdavac;
	}


	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
	}


	public List<Glasanje> getGlasanje() {
		return glasanje;
	}


	public void setGlasanje(List<Glasanje> glasanje) {
		this.glasanje = glasanje;
	}


	}
