package org.tmf.dsmapi.schedule.event;

import java.util.Date;
import javax.ejb.Local;
import org.tmf.dsmapi.appointment.model.Schedule;


@Local
public interface ScheduleEventPublisherLocal {

    void publish(ScheduleEvent event);

    /**
     *
     * CreateNotification
     * @param bean the bean which has been created
     * @param date the creation date
     */
    public void createNotification(Schedule bean, Date date);

    /**
     *
     * DeletionNotification
     * @param bean the bean which has been deleted
     * @param date the deletion date
     */
    public void deletionNotification(Schedule bean, Date date);

    /**
     *
     * UpdateNotification (PATCH)
     * @param bean the bean which has been updated
     * @param date the update date
     */
    public void updateNotification(Schedule bean, Date date);

    /**
     *
     * ValueChangeNotification
     * @param bean the bean which has been changed
     * @param date the change date
     */
    public void valueChangedNotification(Schedule bean, Date date);

    public void statusChangedNotification(Schedule bean, Date date);

}
