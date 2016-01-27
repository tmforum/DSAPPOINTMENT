package org.tmf.dsmapi.freeSlot.event;

import javax.ejb.Local;
import org.tmf.dsmapi.hub.Hub;

@Local
public interface FreeSlotRESTEventPublisherLocal {

    public void publish(Hub hub, FreeSlotEvent event);
    
}
