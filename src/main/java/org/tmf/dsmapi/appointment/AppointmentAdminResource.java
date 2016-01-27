package org.tmf.dsmapi.appointment;

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
import org.tmf.dsmapi.appointment.model.Appointment;
import org.tmf.dsmapi.appointment.AppointmentFacade;
import org.tmf.dsmapi.appointment.event.AppointmentEvent;
import org.tmf.dsmapi.appointment.event.AppointmentEventFacade;
import org.tmf.dsmapi.appointment.event.AppointmentEventPublisherLocal;

@Stateless
@Path("admin/appointment")
public class AppointmentAdminResource {

    @EJB
    AppointmentFacade appointmentFacade;
    @EJB
    AppointmentEventFacade eventFacade;

    @GET
    @Produces({"application/json"})
    public List<Appointment> findAll() {
        return appointmentFacade.findAll();
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
    public Response post(List<Appointment> entities) {

        if (entities == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        int previousRows = appointmentFacade.count();
        int affectedRows=0;

        // Try to persist entities
        try {
            for (Appointment entitie : entities) {
//                appointmentFacade.checkCreation(entitie);
                appointmentFacade.create(entitie);
//                entitie.setHref(info.getAbsolutePath() + "/" + Long.toString(entitie.getId()));
//                appointmentFacade.edit(entitie);
                affectedRows = affectedRows + 1;
            }
        } catch (BadUsageException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        Report stat = new Report(appointmentFacade.count());
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
    public Response update(@PathParam("id") String id, Appointment entity) throws UnknownResourceException {
        Response response = null;
        Appointment appointment = appointmentFacade.find(id);
        if (appointment != null) {
            entity.setId(id);
            appointmentFacade.edit(entity);
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
        int previousRows = appointmentFacade.count();
        appointmentFacade.removeAll();
        List<Appointment> pis = appointmentFacade.findAll();
        for (Appointment pi : pis) {
            delete(pi.getId());
        }

        int currentRows = appointmentFacade.count();
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
        int previousRows = appointmentFacade.count();
        Appointment entity = appointmentFacade.find(id);

        try {
            //Pause for 4 seconds to finish notification
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AppointmentAdminResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        // remove event(s) binding to the resource
        List<AppointmentEvent> events = eventFacade.findAll();
        for (AppointmentEvent event : events) {
            if (event.getResource().getId().equals(id)) {
                eventFacade.remove(event.getId());
            }
        }
        //remove resource
        appointmentFacade.remove(id);

        int affectedRows = 1;
        Report stat = new Report(appointmentFacade.count());
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        // 200 
        Response response = Response.ok(stat).build();
        return response;
    }

    @GET
    @Produces({"application/json"})
    @Path("event")
    public List<AppointmentEvent> findAllEvents() {
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
        List<AppointmentEvent> events = eventFacade.findAll();
        for (AppointmentEvent event : events) {
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
        return new Report(appointmentFacade.count());
    }
}
