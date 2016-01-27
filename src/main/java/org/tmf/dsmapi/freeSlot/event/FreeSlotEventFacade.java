package org.tmf.dsmapi.freeSlot.event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.facade.AbstractFacade;

@Stateless
public class FreeSlotEventFacade extends AbstractFacade<FreeSlotEvent>{
    
    @PersistenceContext(unitName = "DSAppointmentPU")
    private EntityManager em;
   

    
    /**
     *
     */
    public FreeSlotEventFacade() {
        super(FreeSlotEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
