/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ppj.gfos25.Facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import ppj.gfos25.Entity.Account;
import ppj.gfos25.Entity.Aufgabe;
import ppj.gfos25.Entity.Aufgabenbehandlung;
import ppj.gfos25.Entity.Kalendar;
import ppj.gfos25.Entity.Termin;
import jakarta.ejb.EJB;
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

    @EJB
    private AufgabeFacade aufgabeFacade;

    @EJB
    private AccountFacade accountFacade;

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
            List<Kalendar> kalender = em.createNamedQuery("Kalendar.findByAccountId", Kalendar.class)
                    .setParameter("accountId", accountId)
                    .getResultList();
            List<Termin> termine = new java.util.ArrayList<>();
            for (Kalendar k : kalender) {
                termine.add(k.getTermin());
            }
            return termine;
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

    public Aufgabenbehandlung addAufgabeToTermin(int terminId, int aufgabeId) {
        try {
            Termin t = getTerminById(terminId);
            Aufgabe a = aufgabeFacade.getAufgabeById(aufgabeId);
            Aufgabenbehandlung ab = new Aufgabenbehandlung();
            ab.setAufgabe(a);
            ab.setTermin(t);
            em.persist(ab);
            return ab;
        } catch (Exception e) {
            return null;
        }
    }   

    public Kalendar addAccountToTermin(int terminId, int accountId) {
        try {
            Termin t = getTerminById(terminId);
            Account a = accountFacade.getAccountById(accountId);
            Kalendar k = new Kalendar();
            k.setAccount(a);
            k.setTermin(t);
            em.persist(k);
            return k;
        } catch (Exception e) {
            return null;
        }
    }   
}
