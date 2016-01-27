package org.tmf.dsmapi.appointment.event;

import javax.ejb.Local;
import org.tmf.dsmapi.hub.Hub;

@Local
public interface AppointmentRESTEventPublisherLocal {

    public void publish(Hub hub, AppointmentEvent event);
    
}
