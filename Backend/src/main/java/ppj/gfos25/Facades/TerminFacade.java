/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ppj.gfos25.Facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import ppj.gfos25.Entity.Termin;
import jakarta.ejb.LocalBean;

/**
 *
 * @author phili
 */
@Stateless
@LocalBean
public class TerminFacade {
    @PersistenceContext
    private EntityManager em;

    public Termin createTermin(Termin t) {
        try {
            em.persist(t);
            em.flush();
            return this.getTerminById(t.getId());
        } catch (Exception e) {
            return null;
        }
    }

    public Termin getTerminById(int id) {
        try {
            return em.createNamedQuery("Termin.findById", Termin.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Termin> getAllTermine() {
        try {
            return em.createNamedQuery("Termin.findAll", Termin.class)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Termin> getTermineForAccountId(int accountId) {
        try {
            return em.createNamedQuery("Termin.findByAccountId", Termin.class)
                    .setParameter("accountId", accountId)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Termin updateTermin(Termin t) {
        try {
            return em.merge(t);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteTermin(int id) {
        try {
            Termin t = getTerminById(id);
            if (t != null) {
                em.remove(t);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
