package ppj.gfos25.Facades;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import ppj.gfos25.Entity.Message;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class MessageFacade {

	@PersistenceContext
	EntityManager em;

	public Message createMessage(Message m) {
		try {
			em.persist(m);
			em.flush();
			Message messageMitId = this.getMessageById(m.getId());
			return messageMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public Message getMessageById(int id) {
		try {
			return em
					.createNamedQuery("Message.findById", Message.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Message> getAllMessages() {
		try {
			return em
					.createNamedQuery("Message.findAll", Message.class)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Message> getUnreadMessagesForAccountId(int id) {
		try {
			return em
					.createNamedQuery("Message.findUnreadByReceiverID", Message.class)
					.setParameter("id", id)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<Message> getMessagesForChat(int senderID, int receiverID) {
		try {
			return em
					.createNamedQuery("Message.findByChat", Message.class)
					.setParameter("senderID", senderID)
					.setParameter("receiverID", receiverID)
					.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
