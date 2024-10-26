/*
 * 
 * 
 */
package ppj.gfos25.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "AUFGABENBEHANDLUNG")
@NamedQueries({
	@NamedQuery(name = "Aufgabenbehandlung.findAll", query = "SELECT a FROM Aufgabenbehandlung a"),
	@NamedQuery(name = "Aufgabenbehandlung.findById", query = "SELECT a FROM Aufgabenbehandlung a WHERE a.id = :id")})
public class Aufgabenbehandlung implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@JoinColumn(name = "AUFGABE", referencedColumnName = "ID")
    @ManyToOne
	private Aufgabe aufgabe;
	@JoinColumn(name = "TERMIN", referencedColumnName = "ID")
    @ManyToOne
	private Termin termin;

	public Aufgabenbehandlung() {
	}

	public Aufgabenbehandlung(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aufgabe getAufgabe() {
		return aufgabe;
	}

	public void setAufgabe(Aufgabe aufgabe) {
		this.aufgabe = aufgabe;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Aufgabenbehandlung)) {
			return false;
		}
		Aufgabenbehandlung other = (Aufgabenbehandlung) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Aufgabenbehandlung[ id=" + id + " ]";
	}
	
}
