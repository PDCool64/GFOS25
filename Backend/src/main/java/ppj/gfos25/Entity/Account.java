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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "ACCOUNT")
@NamedQueries({
	@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
	@NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
	@NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
	@NamedQuery(name = "Account.findByVorname", query = "SELECT a FROM Account a WHERE a.vorname = :vorname"),
	@NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
	@NamedQuery(name = "Account.findByGeburtsdatum", query = "SELECT a FROM Account a WHERE a.geburtsdatum = :geburtsdatum"),
	@NamedQuery(name = "Account.findByPassworthash", query = "SELECT a FROM Account a WHERE a.passworthash = :passworthash"),
	@NamedQuery(name = "Account.findByIsadmin", query = "SELECT a FROM Account a WHERE a.isadmin = :isadmin")})
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "VORNAME")
	private String vorname;
	@Column(name = "NAME")
	private String name;
	@Column(name = "GEBURTSDATUM")
	private String geburtsdatum;
	@Column(name = "PASSWORTHASH")
	private String passworthash;
	@Column(name = "ISADMIN")
	private Boolean isadmin;

	public Account() {
	}

	public Account(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getPassworthash() {
		return passworthash;
	}

	public void setPassworthash(String passworthash) {
		this.passworthash = passworthash;
	}

	public Boolean getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
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
		return "ppj.gfos25.Application.Account[ id=" + id + " ]";
	}
	
}
