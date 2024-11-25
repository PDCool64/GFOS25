/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ppj.gfos25.Facades;

import java.util.List;
import java.util.ArrayList;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ppj.gfos25.Entity.Account;
import ppj.gfos25.Entity.Aufgabe;
import ppj.gfos25.Entity.Aufgabenbearbeitung;
import jakarta.ejb.LocalBean;

/**
 *
 * @author phili
 */
@Stateless
@LocalBean
public class AufgabeFacade {
    @PersistenceContext
    private EntityManager em;

    public Aufgabe createAufgabe(Aufgabe a) {
        try {
            em.persist(a);
            em.flush();
            Aufgabe aufgabeMitId = this.getAufgabeById(a.getId());
            return aufgabeMitId;
        } catch (Exception e) {
            return null;
        }
    }

    public Aufgabe getAufgabeById(int id) {
        return em.find(Aufgabe.class, id);
    }

    public List<Aufgabe> getAllAufgaben() {
        return em.createQuery("SELECT a FROM Aufgabe a", Aufgabe.class).getResultList();
    }

    public List<Aufgabe> getAufgabenByStatus(int status) {
        return em.createQuery("SELECT a FROM Aufgabe a WHERE a.status = :status", Aufgabe.class).getResultList();

    }
    // TODO: richtig machen
    public List<Aufgabe> getAufgabenByMitarbeiter(Account mitarbeiter) {
        List<Aufgabenbearbeitung> bearbeitung = em
                .createQuery("SELECT b FROM Aufgabenbearbeitung b WHERE b.mitarbeiter = :mitarbeiter",
                        Aufgabenbearbeitung.class)
                .setParameter("mitarbeiter", mitarbeiter).getResultList();
        List<Aufgabe> aufgaben = new ArrayList<Aufgabe>();
        for (Aufgabenbearbeitung a : bearbeitung) {
            aufgaben.add(a.getAufgabe());
        }
        return aufgaben;
    }
}
