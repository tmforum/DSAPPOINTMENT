package org.tmf.dsmapi.freeSlot.event;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.appointment.model.FreeSlot;
import org.tmf.dsmapi.hub.Hub;
import org.tmf.dsmapi.hub.HubFacade;

/**
 *
 * Should be async or called with MDB
 */
@Stateless
@Asynchronous
public class FreeSlotEventPublisher implements FreeSlotEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    FreeSlotEventFacade eventFacade;
    @EJB
    FreeSlotRESTEventPublisherLocal restEventPublisherLocal;

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
    public void publish(FreeSlotEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(FreeSlotEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(FreeSlot bean, Date date) {
        FreeSlotEvent event = new FreeSlotEvent();
        event.setEventTime(date);
        event.setEventType(FreeSlotEventTypeEnum.FreeSlotCreationNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deletionNotification(FreeSlot bean, Date date) {
        FreeSlotEvent event = new FreeSlotEvent();
        event.setEventTime(date);
        event.setEventType(FreeSlotEventTypeEnum.FreeSlotDeletionNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(FreeSlot bean, Date date) {
        FreeSlotEvent event = new FreeSlotEvent();
        event.setEventTime(date);
        event.setEventType(FreeSlotEventTypeEnum.FreeSlotUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

    @Override
    public void valueChangedNotification(FreeSlot bean, Date date) {
        FreeSlotEvent event = new FreeSlotEvent();
        event.setEventTime(date);
        event.setEventType(FreeSlotEventTypeEnum.FreeSlotValueChangeNotification);
        event.setResource(bean);
        publish(event);
    }

    @Override
    public void statusChangedNotification(FreeSlot bean, Date date) {
        FreeSlotEvent event = new FreeSlotEvent();
        event.setEventTime(date);
        event.setEventType(FreeSlotEventTypeEnum.FreeSlotStatusChangedNotification);
        event.setResource(bean);
        publish(event);
    }

}
