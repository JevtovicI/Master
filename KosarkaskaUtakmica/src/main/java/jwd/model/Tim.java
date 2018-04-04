package jwd.model;

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
import javax.validation.constraints.Max;

@Entity
@Table
public class Tim {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String ime;
	@Column
	private String trener;
	@Column
	private String boje;
//	@Max(12)
	@OneToMany(mappedBy = "tim", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Igrac> igraci = new ArrayList<>();

	public Tim() {
		super();
	}

	public Tim(Long id, String ime, String trener, String boje, List<Igrac> igraci) {
		super();
		this.id = id;
		this.ime = ime;
		this.trener = trener;
		this.boje = boje;
		this.igraci = igraci;
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

	public String getTrener() {
		return trener;
	}

	public void setTrener(String trener) {
		this.trener = trener;
	}

	public String getBoje() {
		return boje;
	}

	public void setBoje(String boje) {
		this.boje = boje;
	}

	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
	}

	public void addIgrac(Igrac igrac) {
		this.igraci.add(igrac);

		if (!this.equals(igrac.getTim())) {
			igrac.setTim(this);
		}
	}

}
