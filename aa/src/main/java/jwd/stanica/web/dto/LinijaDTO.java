package jwd.stanica.web.dto;


public class LinijaDTO {
	
	private Long id;
	private Long brojMesta;
	private Integer cenaKarte;
	private String vremePolaska;
	private String destinacija;
	private Long prevoznikId;
	private String prevoznikNaziv;
	
	
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
	public Long getPrevoznikId() {
		return prevoznikId;
	}
	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}
	public String getPrevoznikNaziv() {
		return prevoznikNaziv;
	}
	public void setPrevoznikNaziv(String prevoznikNaziv) {
		this.prevoznikNaziv = prevoznikNaziv;
	}
	
		
}
