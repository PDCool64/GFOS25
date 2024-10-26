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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "ACCOUNT")
@NamedQueries({
	@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
	@NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
	@NamedQuery(name = "Account.findByVorname", query = "SELECT a FROM Account a WHERE a.vorname = :vorname"),
	@NamedQuery(name = "Account.findByNachname", query = "SELECT a FROM Account a WHERE a.nachname = :nachname"),
	@NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
	@NamedQuery(name = "Account.findByTelefonnummer", query = "SELECT a FROM Account a WHERE a.telefonnummer = :telefonnummer"),
	@NamedQuery(name = "Account.findByRang", query = "SELECT a FROM Account a WHERE a.rang = :rang"),
	@NamedQuery(name = "Account.findByUtcOffset", query = "SELECT a FROM Account a WHERE a.utcOffset = :utcOffset"),
	@NamedQuery(name = "Account.findByRegion", query = "SELECT a FROM Account a WHERE a.region = :region")})
public class Account implements Serializable {

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
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "TELEFONNUMMER")
	private String telefonnummer;
	@Basic(optional = false)
    @Column(name = "RANG")
	private int rang;
	@Column(name = "UTC_OFFSET")
	private Integer utcOffset;
	@Column(name = "REGION")
	private String region;
	@JoinColumn(name = "EINSTELLUNGEN", referencedColumnName = "ID")
    @ManyToOne
	private Settings einstellungen;
	@OneToMany(mappedBy = "account")
	private List<Kalendar> kalendarList;
	@OneToMany(mappedBy = "bearbeiter")
	private List<Aufgabenbearbeitung> aufgabenbearbeitungList;
	@OneToMany(mappedBy = "ansprechpartner")
	private List<Kunde> kundeList;

	public Account() {
	}

	public Account(Integer id) {
		this.id = id;
	}

	public Account(Integer id, String vorname, String nachname, int rang) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.rang = rang;
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

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public Integer getUtcOffset() {
		return utcOffset;
	}

	public void setUtcOffset(Integer utcOffset) {
		this.utcOffset = utcOffset;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Settings getEinstellungen() {
		return einstellungen;
	}

	public void setEinstellungen(Settings einstellungen) {
		this.einstellungen = einstellungen;
	}

	public List<Kalendar> getKalendarList() {
		return kalendarList;
	}

	public void setKalendarList(List<Kalendar> kalendarList) {
		this.kalendarList = kalendarList;
	}

	public List<Aufgabenbearbeitung> getAufgabenbearbeitungList() {
		return aufgabenbearbeitungList;
	}

	public void setAufgabenbearbeitungList(List<Aufgabenbearbeitung> aufgabenbearbeitungList) {
		this.aufgabenbearbeitungList = aufgabenbearbeitungList;
	}

	public List<Kunde> getKundeList() {
		return kundeList;
	}

	public void setKundeList(List<Kunde> kundeList) {
		this.kundeList = kundeList;
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
		if (!(object instanceof Account)) {
			return false;
		}
		Account other = (Account) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Account[ id=" + id + " ]";
	}
	
}
