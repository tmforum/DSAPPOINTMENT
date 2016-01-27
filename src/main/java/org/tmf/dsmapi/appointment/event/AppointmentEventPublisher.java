package org.tmf.dsmapi.appointment.event;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.appointment.model.Appointment;
import org.tmf.dsmapi.hub.Hub;
import org.tmf.dsmapi.hub.HubFacade;

/**
 *
 * Should be async or called with MDB
 */
@Stateless
@Asynchronous
public class AppointmentEventPublisher implements AppointmentEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    AppointmentEventFacade eventFacade;
    @EJB
    AppointmentRESTEventPublisherLocal restEventPublisherLocal;

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
    public void publish(AppointmentEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(AppointmentEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(Appointment bean, Date date) {
        AppointmentEvent event = new AppointmentEvent();
        event.setEventTime(date);
        event.setEventType(AppointmentEventTypeEnum.AppointmentCreationNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deletionNotification(Appointment bean, Date date) {
        AppointmentEvent event = new AppointmentEvent();
        event.setEventTime(date);
        event.setEventType(AppointmentEventTypeEnum.AppointmentDeletionNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(Appointment bean, Date date) {
        AppointmentEvent event = new AppointmentEvent();
        event.setEventTime(date);
        event.setEventType(AppointmentEventTypeEnum.AppointmentUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

    @Override
    public void valueChangedNotification(Appointment bean, Date date) {
        AppointmentEvent event = new AppointmentEvent();
        event.setEventTime(date);
        event.setEventType(AppointmentEventTypeEnum.AppointmentValueChangeNotification);
        event.setResource(bean);
        publish(event);
    }

    @Override
    public void statusChangedNotification(Appointment bean, Date date) {
        AppointmentEvent event = new AppointmentEvent();
        event.setEventTime(date);
        event.setEventType(AppointmentEventTypeEnum.AppointmentStatusChangedNotification);
        event.setResource(bean);
        publish(event);
    }

}
