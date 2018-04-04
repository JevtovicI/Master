package jwd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Faul {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Igrac igrac;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Igrac getIgrac() {
		return igrac;
	}

	public void setIgrac(Igrac igrac) {
		this.igrac = igrac;
		if (igrac != null && !igrac.getFaulovi().contains(this)) {
			igrac.getFaulovi().add(this);
		}
	}
}
