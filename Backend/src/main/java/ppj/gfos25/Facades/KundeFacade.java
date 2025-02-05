/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ppj.gfos25.Facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ppj.gfos25.Entity.Kunde;

import java.util.List;

import jakarta.ejb.LocalBean;

/**
 *
 * @author phili
 */
@Stateless
@LocalBean
public class KundeFacade {

	@PersistenceContext
	private EntityManager em;

    public Kunde createKunde(Kunde k) {
        try {
            em.persist(k);
            em.flush();
            Kunde kundeMitId = this.getKundeById(k.getId());
            return kundeMitId;
        } catch (Exception e) {
            return null;
        }       
    }

    public Kunde getKundeById(int id) {
        try {
            return em.createNamedQuery("Kunde.findById", Kunde.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }   

    public List<Kunde> getAllKunden() {
        try {
            return em.createNamedQuery("Kunde.findAll", Kunde.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Kunde updateKunde(Kunde k) {
        try {
            em.merge(k);
            em.flush();
            Kunde kundeMitId = this.getKundeById(k.getId());
            return kundeMitId;
        } catch (Exception e) {
            return null;
        }
    }
    
}
