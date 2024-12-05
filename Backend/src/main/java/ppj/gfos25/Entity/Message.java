/*
 * 
 * 
 */
package ppj.gfos25.Entity;

import jakarta.inject.Named;
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
@Table(name = "MESSAGE")
@NamedQueries({
	@NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
	@NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
	@NamedQuery(name = "Message.findBySenderId", query = "SELECT m FROM Message m WHERE m.sender.id = :id"),
	@NamedQuery(name = "Message.findByReceiverId", query = "SELECT m FROM Message m WHERE m.receiver.id = :id"),
	@NamedQuery(name = "Message.findUnreadByReceiverID", query = "SELECT m FROM Message m WHERE m.receiver.id = :id AND m.isRead = false"),
	@NamedQuery(name = "Message.findByContent", query = "SELECT m FROM Message m WHERE m.content = :content"),
	@NamedQuery(name = "Message.findByTimeSent", query = "SELECT m FROM Message m WHERE m.timeSent = :timeSent"),
	@NamedQuery(name = "Message.findByIsReceived", query = "SELECT m FROM Message m WHERE m.isReceived = :isReceived"),
	@NamedQuery(name = "Message.findByChat", query = "SELECT m FROM Message m WHERE m.sender.id = :senderID AND m.receiver.id = :receiverID"),
	@NamedQuery(name = "Message.findByIsRead", query = "SELECT m FROM Message m WHERE m.isRead = :isRead")})
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "TIME_SENT")
	private String timeSent;
	@Column(name = "IS_RECEIVED")
	private Boolean isReceived;
	@Column(name = "IS_READ")
	private Boolean isRead;
	@JoinColumn(name = "RECEIVER", referencedColumnName = "ID")
    @ManyToOne
	private Account receiver;
	@JoinColumn(name = "SENDER", referencedColumnName = "ID")
    @ManyToOne
	private Account sender;

	public Message() {
	}

	public Message(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}

	public Boolean getIsReceived() {
		return isReceived;
	}

	public void setIsReceived(Boolean isReceived) {
		this.isReceived = isReceived;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
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
		if (!(object instanceof Message)) {
			return false;
		}
		Message other = (Message) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ppj.gfos25.Entity.Message[ id=" + id + " ]";
	}
	
}
