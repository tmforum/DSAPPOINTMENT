package org.tmf.dsmapi.appointment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.tmf.dsmapi.commons.jaxrs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.diff.JsonDiff;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.Jackson;
import org.tmf.dsmapi.commons.utils.URIParser;
import org.tmf.dsmapi.appointment.model.Appointment;
import org.tmf.dsmapi.appointment.AppointmentFacade;
import org.tmf.dsmapi.appointment.event.AppointmentEventPublisherLocal;
import org.tmf.dsmapi.appointment.model.Schedule;
import org.tmf.dsmapi.schedule.ScheduleFacade;

@Stateless
@Path("/appointment/v1/appointment")
public class AppointmentResource {

    @EJB
    AppointmentFacade appointmentFacade;
    @EJB
    ScheduleFacade scheduleFacade;
    @EJB
    AppointmentEventPublisherLocal publisher;

    public AppointmentResource() {
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(Appointment entity,@Context UriInfo info) throws BadUsageException, UnknownResourceException {
        appointmentFacade.checkCreation(entity);
        appointmentFacade.create(entity);
        entity.setHref(info.getAbsolutePath() + "/" + entity.getId());
        appointmentFacade.edit(entity);
        publisher.createNotification(entity, new Date());
        // 201
        Response response = Response.status(Response.Status.CREATED).entity(entity).build();
        return response;
    }

    @GET
    @Produces({"application/json"})
    public Response find(@Context UriInfo info) throws BadUsageException {

        // search queryParameters
        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();

        Map<String, List<String>> mutableMap = new HashMap();
        for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
            mutableMap.put(e.getKey(), e.getValue());
        }

        // fields to filter view
        Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

        Set<Appointment> resultList = findByCriteria(mutableMap);

        Response response;
        if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
            response = Response.ok(resultList).build();
        } else {
            fieldSet.add(URIParser.ID_FIELD);
            List<ObjectNode> nodeList = Jackson.createNodes(resultList, fieldSet);
            response = Response.ok(nodeList).build();
        }
        return response;
    }

    // return Set of unique elements to avoid List with same elements in case of join
    private Set<Appointment> findByCriteria(Map<String, List<String>> criteria) throws BadUsageException {

        List<Appointment> resultList = null;
        if (criteria != null && !criteria.isEmpty()) {
            resultList = appointmentFacade.findByCriteria(criteria, Appointment.class);
        } else {
            resultList = appointmentFacade.findAll();
        }
        if (resultList == null) {
            return new LinkedHashSet<Appointment>();
        } else {
            return new LinkedHashSet<Appointment>(resultList);
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response get(@PathParam("id") String id, @Context UriInfo info) throws UnknownResourceException {

        // search queryParameters
        MultivaluedMap<String, String> queryParameters = info.getQueryParameters();

        Map<String, List<String>> mutableMap = new HashMap();
        for (Map.Entry<String, List<String>> e : queryParameters.entrySet()) {
            mutableMap.put(e.getKey(), e.getValue());
        }

        // fields to filter view
        Set<String> fieldSet = URIParser.getFieldsSelection(mutableMap);

        Appointment appointment = appointmentFacade.find(id);
        Response response;

        // If the result list (list of bills) is not empty, it conains only 1 unique bill
        if (appointment != null) {
            // 200
            if (fieldSet.isEmpty() || fieldSet.contains(URIParser.ALL_FIELDS)) {
                response = Response.ok(appointment).build();
            } else {
                fieldSet.add(URIParser.ID_FIELD);
                ObjectNode node = Jackson.createNode(appointment, fieldSet);
                response = Response.ok(node).build();
            }
        } else {
            // 404 not found
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    @PATCH
    @Path("{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response patch(@PathParam("id") String id, Appointment partialEntity) throws BadUsageException, UnknownResourceException {
        Response response = null;
        Appointment currentEntity = appointmentFacade.patchAttributs(id, partialEntity);

        // 200 OK + location
        response = Response.status(Response.Status.OK).entity(currentEntity).build();

        return response;
    }

    @PATCH
    @Path("{id}")
    @Consumes("application/json-patch+json")
    @Produces({"application/json"})
    public Response jsonPatch(@PathParam("id") String id, JsonPatch patch) throws BadUsageException, UnknownResourceException, JsonPatchException, JsonProcessingException {
        Response response = null;
        //recup de l'entity persistée
        Appointment currentEntity = appointmentFacade.find(id);
        
        //creation d'un JsonNode avec l'entity persistée
        ObjectMapper mapper = new ObjectMapper();
        JsonNode currentNode = mapper.convertValue(currentEntity, JsonNode.class);
        //création d'un JsonNode patched
        final JsonNode patchedNode = patch.apply(currentNode);
        //check RG Patch
        Appointment patchEntity = mapper.convertValue(patchedNode, Appointment.class);
        //appointmentFacade.checkPatchAttributs(patchEntity);
        //transforme le JsonNode en Bean
        Appointment patchCategory = mapper.treeToValue(patchedNode, Appointment.class);
        //appel à la persistence
        appointmentFacade.edit(patchCategory);
        // 200 OK + location
        response = Response.status(Response.Status.OK).entity(patchCategory).build();
        return response;
    }

    @POST
    @Path("getjsonpatch/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response getJsonPatch(@PathParam("id") String id, Appointment patchEntity) throws BadUsageException, UnknownResourceException {
        Response response = null;
        Appointment currentEntity = appointmentFacade.find(id);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode currentNode = mapper.convertValue(currentEntity, JsonNode.class);
        JsonNode patchNode = mapper.convertValue(patchEntity, JsonNode.class);

        JsonPatch patch = JsonDiff.asJsonPatch(currentNode, patchNode);

        // 200 OK + location
        response = Response.status(Response.Status.OK).entity(patch).build();

        return response;
    }

    @POST
    @Path("{appointmentId}/schedule")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response modifySchedule(@PathParam("appointmentId") String appointmentId, Schedule entity) throws BadUsageException, UnknownResourceException {
        scheduleFacade.checkCreation(entity);
        scheduleFacade.create(entity);
//        entity.setHref(info.getAbsolutePath() + "/" + Long.toString(entity.getId()));
//        scheduleFacade.edit(entity);
//        publisher.createNotification(entity, new Date());
        // 201
        Response response = Response.status(Response.Status.CREATED).entity(entity).build();
        return response;
    }

}
