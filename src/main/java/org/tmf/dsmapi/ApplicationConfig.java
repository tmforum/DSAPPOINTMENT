package org.tmf.dsmapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.tmf.dsmapi.commons.jaxrs.JacksonFeature;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig(){
        packages ("com.fasterxml.jackson.jaxrs.json");
        register(JacksonFeature.class);
        register(org.tmf.dsmapi.commons.jaxrs.BadUsageExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.JacksonConfigurator.class);
        register(org.tmf.dsmapi.commons.jaxrs.JsonMappingExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.UnknowResourceExceptionMapper.class);
        register(org.tmf.dsmapi.commons.jaxrs.UnknowResourceExceptionMapper.class);
        register(org.tmf.dsmapi.hub.HubResource.class);
        register(org.tmf.dsmapi.appointment.AppointmentResource.class);
        register(org.tmf.dsmapi.appointment.AppointmentAdminResource.class);
        register(org.tmf.dsmapi.schedule.ScheduleResource.class);
        register(org.tmf.dsmapi.schedule.ScheduleAdminResource.class);
        register(org.tmf.dsmapi.freeSlot.FreeSlotResource.class);
        register(org.tmf.dsmapi.freeSlot.FreeSlotAdminResource.class);
    }

}
