package org.tmf.dsmapi.freeSlot.event;

import java.util.Date;
import javax.ejb.Local;
import org.tmf.dsmapi.appointment.model.FreeSlot;


@Local
public interface FreeSlotEventPublisherLocal {

    void publish(FreeSlotEvent event);

    /**
     *
     * CreateNotification
     * @param bean the bean which has been created
     * @param date the creation date
     */
    public void createNotification(FreeSlot bean, Date date);

    /**
     *
     * DeletionNotification
     * @param bean the bean which has been deleted
     * @param date the deletion date
     */
    public void deletionNotification(FreeSlot bean, Date date);

    /**
     *
     * UpdateNotification (PATCH)
     * @param bean the bean which has been updated
     * @param date the update date
     */
    public void updateNotification(FreeSlot bean, Date date);

    /**
     *
     * ValueChangeNotification
     * @param bean the bean which has been changed
     * @param date the change date
     */
    public void valueChangedNotification(FreeSlot bean, Date date);

    public void statusChangedNotification(FreeSlot bean, Date date);

}
