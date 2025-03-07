/*
 * 
 * 
 */
package ppj.gfos25.Entity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
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
import jakarta.persistence.Transient;

import java.io.Serializable;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "AUFGABENBEARBEITUNG")
@NamedQueries({
	@NamedQuery(name = "Aufgabenbearbeitung.findAll", query = "SELECT a FROM Aufgabenbearbeitung a"),
	@NamedQuery(name = "Aufgabenbearbeitung.findById", query = "SELECT a FROM Aufgabenbearbeitung a WHERE a.id = :id"), 
	@NamedQuery(name = "Aufgabenbearbeitung.findByAccountId", query = "SELECT a FROM Aufgabenbearbeitung a WHERE a.bearbeiter.id = :bearbeiterId")
})
public class Aufgabenbearbeitung implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@JoinColumn(name = "BEARBEITER", referencedColumnName = "ID")
    @ManyToOne
	private Account bearbeiter;
	@JoinColumn(name = "AUFGABE", referencedColumnName = "ID")
    @ManyToOne
	@JsonbTransient
	private Aufgabe aufgabe;

	@Transient
	@JsonbProperty("aufgabeId")
	private Integer aufgabeId;

	public Aufgabenbearbeitung() {
	}

	public Aufgabenbearbeitung(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getBearbeiter() {
		return bearbeiter;
	}

	public void setBearbeiter(Account bearbeiter) {
		this.bearbeiter = bearbeiter;
	}

	public Aufgabe getAufgabe() {
		return aufgabe;
	}


	public void setAufgabe(Aufgabe aufgabe) {
		this.aufgabe = aufgabe;
		this.aufgabeId = aufgabe != null ? aufgabe.getId() : null;
	}

	public Integer getAufgabeId() {
        if (this.aufgabeId == null && aufgabe != null) {
            aufgabeId = aufgabe.getId();
        }
        return aufgabeId;
    }

	public void setAufgabeId(Integer aufgabeId) {
		this.aufgabeId = aufgabeId;
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
		if (!(object instanceof Aufgabenbearbeitung)) {
			return false;
		}
		Aufgabenbearbeitung other = (Aufgabenbearbeitung) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Aufgabenbearbeitung[ id=" + id + " ]";
	}
	
}
