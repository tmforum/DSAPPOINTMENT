package org.tmf.dsmapi.schedule.event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.facade.AbstractFacade;

@Stateless
public class ScheduleEventFacade extends AbstractFacade<ScheduleEvent>{
    
    @PersistenceContext(unitName = "DSAppointmentPU")
    private EntityManager em;
   

    
    /**
     *
     */
    public ScheduleEventFacade() {
        super(ScheduleEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
