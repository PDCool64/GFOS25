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
		@NamedQuery(name = "Account.findByRegion", query = "SELECT a FROM Account a WHERE a.region = :region"),
		@NamedQuery(name = "Account.findByRefreshToken", query = "SELECT a FROM Account a WHERE a.refreshToken = :refreshToken") })
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
	@Column(name = "RANG")
	private Integer rang;
	@Column(name = "UTC_OFFSET")
	private Integer utcOffset;
	@Column(name = "REGION")
	private String region;
	@Column(name = "REFRESH_TOKEN")
	private String refreshToken;
	@JsonbTransient
	@OneToMany(mappedBy = "receiver")
	private List<Message> messageList;
	@JsonbTransient
	@OneToMany(mappedBy = "sender")
	private List<Message> messageList1;
	@JoinColumn(name = "EINSTELLUNGEN", referencedColumnName = "ID")
	@ManyToOne
	private Settings einstellungen;

	public Account() {
	}

	public Account(Integer id) {
		this.id = id;
	}

	public Account(Integer id, String vorname, String nachname) {
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

	public Integer getRang() {
		return rang;
	}

	public void setRang(Integer rang) {
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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public List<Message> getMessageList1() {
		return messageList1;
	}

	public void setMessageList1(List<Message> messageList1) {
		this.messageList1 = messageList1;
	}

	public Settings getEinstellungen() {
		return einstellungen;
	}

	public void setEinstellungen(Settings einstellungen) {
		this.einstellungen = einstellungen;
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
