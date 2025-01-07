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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "AUFGABE")
@NamedQueries({
		@NamedQuery(name = "Aufgabe.findAll", query = "SELECT a FROM Aufgabe a"),
		@NamedQuery(name = "Aufgabe.findById", query = "SELECT a FROM Aufgabe a WHERE a.id = :id"),
		@NamedQuery(name = "Aufgabe.findByTitel", query = "SELECT a FROM Aufgabe a WHERE a.titel = :titel"),
		@NamedQuery(name = "Aufgabe.findByBeschreibung", query = "SELECT a FROM Aufgabe a WHERE a.beschreibung = :beschreibung"),
		@NamedQuery(name = "Aufgabe.findByErstellungsdatum", query = "SELECT a FROM Aufgabe a WHERE a.erstellungsdatum = :erstellungsdatum"),
		@NamedQuery(name = "Aufgabe.findByFaelligkeitsdatum", query = "SELECT a FROM Aufgabe a WHERE a.faelligkeitsdatum = :faelligkeitsdatum"),
		@NamedQuery(name = "Aufgabe.findByPrioritaet", query = "SELECT a FROM Aufgabe a WHERE a.prioritaet = :prioritaet"),
		@NamedQuery(name = "Aufgabe.findByStatus", query = "SELECT a FROM Aufgabe a WHERE a.status = :status") })
public class Aufgabe implements Serializable {

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
	@Column(name = "ERSTELLUNGSDATUM")
	private String erstellungsdatum;
	@Column(name = "FAELLIGKEITSDATUM")
	private String faelligkeitsdatum;
	@Column(name = "PRIORITAET")
	private Integer prioritaet;
	@Column(name = "STATUS")
	private Integer status;
	@JoinColumn(name = "KUNDE", referencedColumnName = "ID")
	@ManyToOne
	private Kunde kunde;
	@OneToMany(mappedBy = "aufgabe")
	@JsonbTransient
	private List<Aufgabenbearbeitung> aufgabenbearbeitungList;
	@OneToMany(mappedBy = "aufgabe")
	@JsonbTransient
	private List<Aufgabenbehandlung> aufgabenbehandlungList;
	@OneToMany(mappedBy = "aufgabe")
	@JsonbTransient
	private List<Aufgabenpunkt> aufgabenpunktList;

	public Aufgabe() {
	}

	public Aufgabe(Integer id) {
		this.id = id;
	}

	public Aufgabe(Integer id, String titel) {
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

	public String getErstellungsdatum() {
		return erstellungsdatum;
	}

	public void setErstellungsdatum(String erstellungsdatum) {
		this.erstellungsdatum = erstellungsdatum;
	}

	public String getFaelligkeitsdatum() {
		return faelligkeitsdatum;
	}

	public void setFaelligkeitsdatum(String faelligkeitsdatum) {
		this.faelligkeitsdatum = faelligkeitsdatum;
	}

	public Integer getPrioritaet() {
		return prioritaet;
	}

	public void setPrioritaet(Integer prioritaet) {
		this.prioritaet = prioritaet;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public List<Aufgabenbearbeitung> getAufgabenbearbeitungList() {
		return aufgabenbearbeitungList;
	}

	public void setAufgabenbearbeitungList(List<Aufgabenbearbeitung> aufgabenbearbeitungList) {
		this.aufgabenbearbeitungList = aufgabenbearbeitungList;
	}

	public List<Aufgabenbehandlung> getAufgabenbehandlungList() {
		return aufgabenbehandlungList;
	}

	public void setAufgabenbehandlungList(List<Aufgabenbehandlung> aufgabenbehandlungList) {
		this.aufgabenbehandlungList = aufgabenbehandlungList;
	}

	public List<Aufgabenpunkt> getAufgabenpunktList() {
		return aufgabenpunktList;
	}

	public void setAufgabenpunktList(List<Aufgabenpunkt> aufgabenpunktList) {
		this.aufgabenpunktList = aufgabenpunktList;
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
		if (!(object instanceof Aufgabe)) {
			return false;
		}
		Aufgabe other = (Aufgabe) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Aufgabe[ id=" + id + " ]";
	}

}
