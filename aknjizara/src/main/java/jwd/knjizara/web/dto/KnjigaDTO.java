package jwd.knjizara.web.dto;


public class KnjigaDTO {
	
	private Long id;
	private String naziv;
	private Integer izdanje;
	private String pisac;
	private String isbn;
	private Integer brojGlasova;
	private Long izdavacId;
	private String izdavacNaziv;
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
	public Long getIzdavacId() {
		return izdavacId;
	}
	public void setIzdavacId(Long izdavacId) {
		this.izdavacId = izdavacId;
	}
	public String getIzdavacNaziv() {
		return izdavacNaziv;
	}
	public void setIzdavacNaziv(String izdavacNaziv) {
		this.izdavacNaziv = izdavacNaziv;
	}
	
	

		
}
