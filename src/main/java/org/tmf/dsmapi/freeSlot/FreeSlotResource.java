package org.tmf.dsmapi.freeSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.ws.rs.POST;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.appointment.model.FreeSlot;
import org.tmf.dsmapi.appointment.model.Schedule;
import org.tmf.dsmapi.freeSlot.FreeSlotFacade;
import org.tmf.dsmapi.freeSlot.event.FreeSlotEventPublisherLocal;

@Stateless
@Path("/appointment/v1/freeSlot")
public class FreeSlotResource {

    @EJB
    FreeSlotFacade freeSlotFacade;
    @EJB
    FreeSlotEventPublisherLocal publisher;

    public FreeSlotResource() {
    }




    @POST
    @Path("/search")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response searchFreeSlot(Schedule entity) throws BadUsageException, UnknownResourceException {
        List<FreeSlot> freeSlots = freeSlotFacade.checkFreeSlot(entity);
//        freeSlotFacade.create(entity);
//        entity.setHref(info.getAbsolutePath() + "/" + Long.toString(entity.getId()));
//        appointmentFacade.edit(entity);
//        publisher.createNotification(entity, new Date());
        // 201
        Response response = Response.status(Response.Status.CREATED).entity(freeSlots).build();
        return response;
    }





}
