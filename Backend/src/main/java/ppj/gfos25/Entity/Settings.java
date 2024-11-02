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
@Table(name = "SETTINGS")
@NamedQueries({
	@NamedQuery(name = "Settings.findAll", query = "SELECT s FROM Settings s"),
	@NamedQuery(name = "Settings.findById", query = "SELECT s FROM Settings s WHERE s.id = :id"),
	@NamedQuery(name = "Settings.findByFarbschema", query = "SELECT s FROM Settings s WHERE s.farbschema = :farbschema"),
	@NamedQuery(name = "Settings.findBySprache", query = "SELECT s FROM Settings s WHERE s.sprache = :sprache"),
	@NamedQuery(name = "Settings.findByZweiFaktorAuth", query = "SELECT s FROM Settings s WHERE s.zweiFaktorAuth = :zweiFaktorAuth"),
	@NamedQuery(name = "Settings.findByPasswortHash", query = "SELECT s FROM Settings s WHERE s.passwortHash = :passwortHash")})
public class Settings implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "FARBSCHEMA")
	private Integer farbschema;
	@Column(name = "SPRACHE")
	private String sprache;
	@Column(name = "ZWEI_FAKTOR_AUTH")
	private Boolean zweiFaktorAuth;
	@Column(name = "PASSWORT_HASH")
	private String passwortHash;

	public Settings() {
	}

	public Settings(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFarbschema() {
		return farbschema;
	}

	public void setFarbschema(Integer farbschema) {
		this.farbschema = farbschema;
	}

	public String getSprache() {
		return sprache;
	}

	public void setSprache(String sprache) {
		this.sprache = sprache;
	}

	public Boolean getZweiFaktorAuth() {
		return zweiFaktorAuth;
	}

	public void setZweiFaktorAuth(Boolean zweiFaktorAuth) {
		this.zweiFaktorAuth = zweiFaktorAuth;
	}

	public String getPasswortHash() {
		return passwortHash;
	}

	public void setPasswortHash(String passwortHash) {
		this.passwortHash = passwortHash;
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
		if (!(object instanceof Settings)) {
			return false;
		}
		Settings other = (Settings) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Settings[ id=" + id + " ]";
	}
	
}
