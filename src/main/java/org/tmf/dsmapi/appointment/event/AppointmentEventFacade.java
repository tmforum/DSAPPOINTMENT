package org.tmf.dsmapi.appointment.event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.facade.AbstractFacade;

@Stateless
public class AppointmentEventFacade extends AbstractFacade<AppointmentEvent>{
    
    @PersistenceContext(unitName = "DSAppointmentPU")
    private EntityManager em;
   

    
    /**
     *
     */
    public AppointmentEventFacade() {
        super(AppointmentEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
