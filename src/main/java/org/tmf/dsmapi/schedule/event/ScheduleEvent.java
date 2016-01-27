package org.tmf.dsmapi.schedule.event;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.tmf.dsmapi.commons.utils.CustomJsonDateSerializer;
import org.tmf.dsmapi.appointment.model.Schedule;

@XmlRootElement
@Entity
@Table(name="Event_Schedule")
@JsonPropertyOrder(value = {"eventId","eventTime", "eventType", "resource"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Date eventTime;

    @Enumerated(value = EnumType.STRING)
    private ScheduleEventTypeEnum eventType;

    private Schedule resource; //check for object

    @JsonProperty("eventId")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public ScheduleEventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(ScheduleEventTypeEnum eventType) {
        this.eventType = eventType;
    }

    @JsonIgnore
    public Schedule getResource() {
        return resource;
    }

    public void setResource(Schedule resource) {
        this.resource = resource;
    }

    @JsonAutoDetect(fieldVisibility = ANY)
    class EventBody {
        private Schedule entity;
        public Schedule getSchedule() {
            return entity;
        }
        public EventBody(Schedule entity) { 
        this.entity = entity;
		}
    }

	@JsonProperty("event")
	public EventBody getEvent() {
	   return new EventBody(getResource() );
	}

    @Override
    public String toString() {
        return "ScheduleEvent{" + "id=" + id + ", eventTime=" + eventTime + ", eventType=" + eventType + ", event=" + resource + '}';
    }

}
