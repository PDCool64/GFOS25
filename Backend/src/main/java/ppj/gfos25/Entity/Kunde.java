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
@Table(name = "KUNDE")
@NamedQueries({
	@NamedQuery(name = "Kunde.findAll", query = "SELECT k FROM Kunde k"),
	@NamedQuery(name = "Kunde.findById", query = "SELECT k FROM Kunde k WHERE k.id = :id"),
	@NamedQuery(name = "Kunde.findByVorname", query = "SELECT k FROM Kunde k WHERE k.vorname = :vorname"),
	@NamedQuery(name = "Kunde.findByNachname", query = "SELECT k FROM Kunde k WHERE k.nachname = :nachname"),
	@NamedQuery(name = "Kunde.findByFirma", query = "SELECT k FROM Kunde k WHERE k.firma = :firma"),
	@NamedQuery(name = "Kunde.findByEmail", query = "SELECT k FROM Kunde k WHERE k.email = :email"),
	@NamedQuery(name = "Kunde.findByTelefonnummer", query = "SELECT k FROM Kunde k WHERE k.telefonnummer = :telefonnummer"),
	@NamedQuery(name = "Kunde.findByKontaktstatus", query = "SELECT k FROM Kunde k WHERE k.kontaktstatus = :kontaktstatus"),
	@NamedQuery(name = "Kunde.findByKundenstatus", query = "SELECT k FROM Kunde k WHERE k.kundenstatus = :kundenstatus"),
	@NamedQuery(name = "Kunde.findByUtcOffset", query = "SELECT k FROM Kunde k WHERE k.utcOffset = :utcOffset")})
public class Kunde implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "VORNAME")
	private String vorname;
	@Basic(optional = false)
    @Column(name = "NACHNAME")
	private String nachname;
	@Column(name = "FIRMA")
	private String firma;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "TELEFONNUMMER")
	private String telefonnummer;
	@Column(name = "KONTAKTSTATUS")
	private Integer kontaktstatus;
	@Column(name = "KUNDENSTATUS")
	private Integer kundenstatus;
	@Column(name = "UTC_OFFSET")
	private Integer utcOffset;
	@OneToMany(mappedBy = "kunde")
	@JsonbTransient
	private List<Aufgabe> aufgabeList;
	@JoinColumn(name = "ANSPRECHPARTNER", referencedColumnName = "ID")
    @ManyToOne
	private Account ansprechpartner;

	public Kunde() {
	}

	public Kunde(Integer id) {
		this.id = id;
	}

	public Kunde(Integer id, String vorname, String nachname) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public Integer getKontaktstatus() {
		return kontaktstatus;
	}

	public void setKontaktstatus(Integer kontaktstatus) {
		this.kontaktstatus = kontaktstatus;
	}

	public Integer getKundenstatus() {
		return kundenstatus;
	}

	public void setKundenstatus(Integer kundenstatus) {
		this.kundenstatus = kundenstatus;
	}

	public Integer getUtcOffset() {
		return utcOffset;
	}

	public void setUtcOffset(Integer utcOffset) {
		this.utcOffset = utcOffset;
	}

	public List<Aufgabe> getAufgabeList() {
		return aufgabeList;
	}

	public void setAufgabeList(List<Aufgabe> aufgabeList) {
		this.aufgabeList = aufgabeList;
	}

	public Account getAnsprechpartner() {
		return ansprechpartner;
	}

	public void setAnsprechpartner(Account ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
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
		if (!(object instanceof Kunde)) {
			return false;
		}
		Kunde other = (Kunde) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Kunde[ id=" + id + " ]";
	}
	
}
