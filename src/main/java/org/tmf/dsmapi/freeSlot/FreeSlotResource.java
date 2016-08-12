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
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadObjectQuery;
import org.tmf.dsmapi.appointment.AppointmentFacade;
import org.tmf.dsmapi.appointment.model.AddressRef;
import org.tmf.dsmapi.appointment.model.Appointment;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.appointment.model.FreeSlot;
import org.tmf.dsmapi.appointment.model.RelatedPartyRef;
import org.tmf.dsmapi.appointment.model.Schedule;
import org.tmf.dsmapi.appointment.model.SlotSearchOut;
import org.tmf.dsmapi.freeSlot.FreeSlotFacade;
import org.tmf.dsmapi.freeSlot.event.FreeSlotEventPublisherLocal;

@Stateless
@Path("/appointment/v1/freeSlot")
public class FreeSlotResource {

    @EJB
    FreeSlotFacade freeSlotFacade;
    @EJB
    AppointmentFacade appointmentFacade;
    @EJB
    FreeSlotEventPublisherLocal publisher;

    public FreeSlotResource() {
    }


    @POST
    @Path("")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response findFreeSlot(Schedule schedule) throws BadUsageException, UnknownResourceException {
        FreeSlot freeSlot = freeSlotFacade.checkFreeSlot(schedule);
        List<FreeSlot> resultList = null;
        SlotSearchOut searchOut = new SlotSearchOut();
//        FreeSlot newSlot = new FreeSlot();;
//        newSlot.setAddress(slot.getAddress());
//        newSlot.setRelatedParty(slot.getRelatedParty());
//        resultList = freeSlotFacade.findByCriteria(newSlot);
        resultList = freeSlotFacade.findByCriteria(freeSlot);
        searchOut.setFreeSlot(resultList);
        
        return Response.ok(searchOut).build();
         
    }



    @POST
    @Path("/search")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response searchFreeSlot(Schedule schedule) throws BadUsageException, UnknownResourceException {
        //List<FreeSlot> freeSlots = freeSlotFacade.checkFreeSlot(entity);
//        freeSlotFacade.create(entity);
//        entity.setHref(info.getAbsolutePath() + "/" + Long.toString(entity.getId()));
//        appointmentFacade.edit(entity);
//        publisher.createNotification(entity, new Date());
        // 201
        
        Appointment appointment = appointmentFacade.find(schedule.getAppointmentId());
        FreeSlot freeSlot = new FreeSlot();
        
        RelatedPartyRef party = new RelatedPartyRef();
        List<RelatedPartyRef> parties = new ArrayList<RelatedPartyRef>();
        for (RelatedPartyRef partyTmp : appointment.getRelatedParty()) {
            party.setId(partyTmp.getId());
            party.setHref(partyTmp.getHref());
            party.setName(partyTmp.getName());
            party.setRole(partyTmp.getRole());
            parties.add(party);
        }
        
        freeSlot.setRelatedParty(parties);
        freeSlot.setEndDate(schedule.getEndDate());
        freeSlot.setStartDate(schedule.getStartDate());
        
        List<FreeSlot> freeSlots =  freeSlotFacade.findByCriteria(freeSlot);
        SlotSearchOut searchOut = new SlotSearchOut();
        searchOut.setFreeSlot(freeSlots);
        Response response = Response.ok(searchOut).build();
        return response;
    }


}
