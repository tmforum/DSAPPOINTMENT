package org.tmf.dsmapi.schedule.event;

import javax.ejb.Local;
import org.tmf.dsmapi.hub.Hub;

@Local
public interface ScheduleRESTEventPublisherLocal {

    public void publish(Hub hub, ScheduleEvent event);
    
}
