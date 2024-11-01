package ppj.gfos25.Facades;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import ppj.gfos25.Entity.Account;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class AccountFacade {
	@PersistenceContext
	private EntityManager em;

	public Account createAccount(Account a) {
		try {
			em.persist(a);
			em.flush();
			Account accountMitId = this.getAccountById(a.getId());
			return accountMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public Account getAccountByEmail(String email) {
		try {
			return em
				.createNamedQuery("Account.findByEmail", Account.class)
				.setParameter("email", email)
				.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Account getAccountById(int id) {
		try {
			return em.createNamedQuery("Account.findById", Account.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Account> getAllAccounts() {
		try {
			return em
				.createNamedQuery("Account.findAll", Account.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public Account updateAccount(Account a) {
		try {
			Account accountInDatenbank = this.getAccountById(a.getId());
			accountInDatenbank.setEmail(a.getEmail());
			accountInDatenbank.setVorname(a.getVorname());
			if (a.getId() == accountInDatenbank.getId()) {
				em.merge(accountInDatenbank);
				em.flush();
			}
			return accountInDatenbank;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteAccount(Account a) {
		try {
			em.remove(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
