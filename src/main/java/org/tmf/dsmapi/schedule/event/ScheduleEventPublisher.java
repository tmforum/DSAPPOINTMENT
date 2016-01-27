package org.tmf.dsmapi.schedule.event;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.appointment.model.Schedule;
import org.tmf.dsmapi.hub.Hub;
import org.tmf.dsmapi.hub.HubFacade;

/**
 *
 * Should be async or called with MDB
 */
@Stateless
@Asynchronous
public class ScheduleEventPublisher implements ScheduleEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    ScheduleEventFacade eventFacade;
    @EJB
    ScheduleRESTEventPublisherLocal restEventPublisherLocal;

    /** 
     * Add business logic below. (Right-click in editor and choose
     * "Insert Code > Add Business Method")
     * Access Hubs using callbacks and send to http publisher 
     *(pool should be configured around the RESTEventPublisher bean)
     * Loop into array of Hubs
     * Call RestEventPublisher - Need to implement resend policy plus eviction
     * Filtering is done in RestEventPublisher based on query expression
    */ 
    @Override
    public void publish(ScheduleEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(ScheduleEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(Schedule bean, Date date) {
        ScheduleEvent event = new ScheduleEvent();
        event.setEventTime(date);
        event.setEventType(ScheduleEventTypeEnum.ScheduleCreationNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deletionNotification(Schedule bean, Date date) {
        ScheduleEvent event = new ScheduleEvent();
        event.setEventTime(date);
        event.setEventType(ScheduleEventTypeEnum.ScheduleDeletionNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(Schedule bean, Date date) {
        ScheduleEvent event = new ScheduleEvent();
        event.setEventTime(date);
        event.setEventType(ScheduleEventTypeEnum.ScheduleUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

    @Override
    public void valueChangedNotification(Schedule bean, Date date) {
        ScheduleEvent event = new ScheduleEvent();
        event.setEventTime(date);
        event.setEventType(ScheduleEventTypeEnum.ScheduleValueChangeNotification);
        event.setResource(bean);
        publish(event);
    }

    @Override
    public void statusChangedNotification(Schedule bean, Date date) {
        ScheduleEvent event = new ScheduleEvent();
        event.setEventTime(date);
        event.setEventType(ScheduleEventTypeEnum.ScheduleStatusChangedNotification);
        event.setResource(bean);
        publish(event);
    }

}
