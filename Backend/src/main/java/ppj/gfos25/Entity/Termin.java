/*
 * 
 * 
 */
package ppj.gfos25.Entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "TERMIN")
@NamedQueries({
	@NamedQuery(name = "Termin.findAll", query = "SELECT t FROM Termin t"),
	@NamedQuery(name = "Termin.findById", query = "SELECT t FROM Termin t WHERE t.id = :id"),
	@NamedQuery(name = "Termin.findByTitel", query = "SELECT t FROM Termin t WHERE t.titel = :titel"),
	@NamedQuery(name = "Termin.findByBeschreibung", query = "SELECT t FROM Termin t WHERE t.beschreibung = :beschreibung"),
	@NamedQuery(name = "Termin.findByStartzeit", query = "SELECT t FROM Termin t WHERE t.startzeit = :startzeit"),
	@NamedQuery(name = "Termin.findByEndzeit", query = "SELECT t FROM Termin t WHERE t.endzeit = :endzeit")})
public class Termin implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "TITEL")
	private String titel;
	@Column(name = "BESCHREIBUNG")
	private String beschreibung;
	@Column(name = "STARTZEIT")
	private String startzeit;
	@Column(name = "ENDZEIT")
	private String endzeit;
	@OneToMany(mappedBy = "termin")
	@JsonbTransient
	private List<Kalendar> kalendarList;
	@OneToMany(mappedBy = "termin")
	@JsonbTransient
	private List<Aufgabenbehandlung> aufgabenbehandlungList;

	public Termin() {
	}

	public Termin(Integer id) {
		this.id = id;
	}

	public Termin(Integer id, String titel) {
		this.id = id;
		this.titel = titel;
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

	public String getStartzeit() {
		return startzeit;
	}

	public void setStartzeit(String startzeit) {
		this.startzeit = startzeit;
	}

	public String getEndzeit() {
		return endzeit;
	}

	public void setEndzeit(String endzeit) {
		this.endzeit = endzeit;
	}

	public List<Kalendar> getKalendarList() {
		return kalendarList;
	}

	public void setKalendarList(List<Kalendar> kalendarList) {
		this.kalendarList = kalendarList;
	}

	public List<Aufgabenbehandlung> getAufgabenbehandlungList() {
		return aufgabenbehandlungList;
	}

	public void setAufgabenbehandlungList(List<Aufgabenbehandlung> aufgabenbehandlungList) {
		this.aufgabenbehandlungList = aufgabenbehandlungList;
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
		if (!(object instanceof Termin)) {
			return false;
		}
		Termin other = (Termin) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Termin[ id=" + id + " ]";
	}
	
}
