package org.tmf.dsmapi.appointment.event;

import java.util.Date;
import javax.ejb.Local;
import org.tmf.dsmapi.appointment.model.Appointment;


@Local
public interface AppointmentEventPublisherLocal {

    void publish(AppointmentEvent event);

    /**
     *
     * CreateNotification
     * @param bean the bean which has been created
     * @param date the creation date
     */
    public void createNotification(Appointment bean, Date date);

    /**
     *
     * DeletionNotification
     * @param bean the bean which has been deleted
     * @param date the deletion date
     */
    public void deletionNotification(Appointment bean, Date date);

    /**
     *
     * UpdateNotification (PATCH)
     * @param bean the bean which has been updated
     * @param date the update date
     */
    public void updateNotification(Appointment bean, Date date);

    /**
     *
     * ValueChangeNotification
     * @param bean the bean which has been changed
     * @param date the change date
     */
    public void valueChangedNotification(Appointment bean, Date date);

    public void statusChangedNotification(Appointment bean, Date date);

}
