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
@Table(name = "KALENDAR")
@NamedQueries({
	@NamedQuery(name = "Kalendar.findAll", query = "SELECT k FROM Kalendar k"),
	@NamedQuery(name = "Kalendar.findById", query = "SELECT k FROM Kalendar k WHERE k.id = :id"),
	@NamedQuery(name = "Kalendar.findByAccountId", query = "SELECT k FROM Kalendar k WHERE k.account.id = :accountId"),
})
public class Kalendar implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    @ManyToOne
	private Account account;
	@JoinColumn(name = "TERMIN", referencedColumnName = "ID")
    @ManyToOne
	@JsonbTransient
	private Termin termin;

	@Transient
	@JsonbProperty("terminId")
	private Integer terminId;

	public Kalendar() {
	}

	public Kalendar(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
		this.terminId = termin != null ? termin.getId() : null;
	}

	public Integer getTerminId() {
        if (this.terminId == null && termin != null) {
            terminId = termin.getId();
        }
        return terminId;
    }

	public void setTerminId(Integer terminId) {
		this.terminId = terminId;
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
		if (!(object instanceof Kalendar)) {
			return false;
		}
		Kalendar other = (Kalendar) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Kalendar[ id=" + id + " ]";
	}
	
}
