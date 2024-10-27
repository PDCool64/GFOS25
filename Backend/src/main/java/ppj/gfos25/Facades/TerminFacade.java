/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ppj.gfos25.Facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    public Termin createTermin(Termin t){
        try {
			em.persist(t);
			em.flush();
			Termin terminMitId = this.getTerminById(t.getId());
			return terminMitId;
		} catch (Exception e) {
			return null;
		}
        
    }

    public Termin getTerminById(int id){
        try {
			return em.find(Termin.class, id);
		} catch (Exception e) {
			return null;
		}

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
