package org.tmf.dsmapi.schedule;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.appointment.model.Schedule;
import org.tmf.dsmapi.schedule.ScheduleFacade;
import org.tmf.dsmapi.schedule.event.ScheduleEventPublisherLocal;

@Stateless
@Path("/appointment/v1/schedule")
public class ScheduleResource {

    @EJB
    ScheduleFacade scheduleFacade;
    @EJB
    ScheduleEventPublisherLocal publisher;

    public ScheduleResource() {
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(Schedule entity) throws BadUsageException, UnknownResourceException  {
        scheduleFacade.checkCreation(entity);
        scheduleFacade.create(entity);
//        entity.setHref(info.getAbsolutePath() + "/" + Long.toString(entity.getId()));
//        scheduleFacade.edit(entity);
        publisher.createNotification(entity, new Date());
        // 201
        Response response = Response.status(Response.Status.CREATED).entity(entity).build();
        return response;
    }







}
