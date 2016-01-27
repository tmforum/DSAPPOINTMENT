package org.tmf.dsmapi.schedule;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.jaxrs.Report;
import org.tmf.dsmapi.appointment.model.Schedule;
import org.tmf.dsmapi.schedule.ScheduleFacade;
import org.tmf.dsmapi.schedule.event.ScheduleEvent;
import org.tmf.dsmapi.schedule.event.ScheduleEventFacade;
import org.tmf.dsmapi.schedule.event.ScheduleEventPublisherLocal;

@Stateless
@Path("admin/schedule")
public class ScheduleAdminResource {

    @EJB
    ScheduleFacade scheduleFacade;
    @EJB
    ScheduleEventFacade eventFacade;

    @GET
    @Produces({"application/json"})
    public List<Schedule> findAll() {
        return scheduleFacade.findAll();
    }

    /**
     *
     * For test purpose only
     * @param entities
     * @return
     */
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response post(List<Schedule> entities) {

        if (entities == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        int previousRows = scheduleFacade.count();
        int affectedRows=0;

        // Try to persist entities
        try {
            for (Schedule entitie : entities) {
//                scheduleFacade.checkCreation(entitie);
                scheduleFacade.create(entitie);
//                entitie.setHref(info.getAbsolutePath() + "/" + Long.toString(entitie.getId()));
//                scheduleFacade.edit(entitie);
                affectedRows = affectedRows + 1;
            }
        } catch (BadUsageException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        Report stat = new Report(scheduleFacade.count());
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        // 201 OK
        return Response.created(null).
                entity(stat).
                build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response update(@PathParam("id") String id, Schedule entity) throws UnknownResourceException {
        Response response = null;
        Schedule schedule = scheduleFacade.find(id);
        if (schedule != null) {
            entity.setId(id);
            scheduleFacade.edit(entity);
            // 200 OK + location
            response = Response.status(Response.Status.OK).entity(entity).build();

        } else {
            // 404 not found
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    /**
     *
     * For test purpose only
     * @return
     * @throws org.tmf.dsmapi.commons.exceptions.UnknownResourceException
     */
    @DELETE
    public Report deleteAll() throws UnknownResourceException {

        eventFacade.removeAll();
        int previousRows = scheduleFacade.count();
        scheduleFacade.removeAll();
        List<Schedule> pis = scheduleFacade.findAll();
        for (Schedule pi : pis) {
            delete(pi.getId());
        }

        int currentRows = scheduleFacade.count();
        int affectedRows = previousRows - currentRows;

        Report stat = new Report(currentRows);
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        return stat;
    }

    /**
     *
     * For test purpose only
     * @param id
     * @return
     * @throws UnknownResourceException
     */
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) throws UnknownResourceException {
        int previousRows = scheduleFacade.count();
        Schedule entity = scheduleFacade.find(id);

        try {
            //Pause for 4 seconds to finish notification
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ScheduleAdminResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        // remove event(s) binding to the resource
        List<ScheduleEvent> events = eventFacade.findAll();
        for (ScheduleEvent event : events) {
            if (event.getResource().getId().equals(id)) {
                eventFacade.remove(event.getId());
            }
        }
        //remove resource
        scheduleFacade.remove(id);

        int affectedRows = 1;
        Report stat = new Report(scheduleFacade.count());
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        // 200 
        Response response = Response.ok(stat).build();
        return response;
    }

    @GET
    @Produces({"application/json"})
    @Path("event")
    public List<ScheduleEvent> findAllEvents() {
        return eventFacade.findAll();
    }

    @DELETE
    @Path("event")
    public Report deleteAllEvent() {

        int previousRows = eventFacade.count();
        eventFacade.removeAll();
        int currentRows = eventFacade.count();
        int affectedRows = previousRows - currentRows;

        Report stat = new Report(currentRows);
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        return stat;
    }

    @DELETE
    @Path("event/{id}")
    public Response deleteEvent(@PathParam("id") String id) throws UnknownResourceException {

        int previousRows = eventFacade.count();
        List<ScheduleEvent> events = eventFacade.findAll();
        for (ScheduleEvent event : events) {
            if (event.getResource().getId().equals(id)) {
                eventFacade.remove(event.getId());

            }
        }
        int currentRows = eventFacade.count();
        int affectedRows = previousRows - currentRows;

        Report stat = new Report(currentRows);
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        // 200 
        Response response = Response.ok(stat).build();
        return response;
    }

    /**
     *
     * @return
     */
    @GET
    @Path("count")
    @Produces({"application/json"})
    public Report count() {
        return new Report(scheduleFacade.count());
    }
}
