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
@Table(name = "AUFGABENPUNKT")
@NamedQueries({
	@NamedQuery(name = "Aufgabenpunkt.findAll", query = "SELECT a FROM Aufgabenpunkt a"),
	@NamedQuery(name = "Aufgabenpunkt.findById", query = "SELECT a FROM Aufgabenpunkt a WHERE a.id = :id"),
	@NamedQuery(name = "Aufgabenpunkt.findByTitel", query = "SELECT a FROM Aufgabenpunkt a WHERE a.titel = :titel"),
	@NamedQuery(name = "Aufgabenpunkt.findByBeschreibung", query = "SELECT a FROM Aufgabenpunkt a WHERE a.beschreibung = :beschreibung"),
	@NamedQuery(name = "Aufgabenpunkt.findByErledigt", query = "SELECT a FROM Aufgabenpunkt a WHERE a.erledigt = :erledigt")})
public class Aufgabenpunkt implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "TITEL")
	private String titel;
	@Column(name = "BESCHREIBUNG")
	private String beschreibung;
	@Column(name = "ERLEDIGT")
	private Boolean erledigt;
	@JoinColumn(name = "AUFGABE", referencedColumnName = "ID")
    @ManyToOne
	private Aufgabe aufgabe;

	public Aufgabenpunkt() {
	}

	public Aufgabenpunkt(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Boolean getErledigt() {
		return erledigt;
	}

	public void setErledigt(Boolean erledigt) {
		this.erledigt = erledigt;
	}

	public Aufgabe getAufgabe() {
		return aufgabe;
	}

	public void setAufgabe(Aufgabe aufgabe) {
		this.aufgabe = aufgabe;
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
		if (!(object instanceof Aufgabenpunkt)) {
			return false;
		}
		Aufgabenpunkt other = (Aufgabenpunkt) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Aufgabenpunkt[ id=" + id + " ]";
	}
	
}
